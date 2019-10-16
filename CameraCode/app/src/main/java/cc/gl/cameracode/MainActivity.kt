package cc.gl.cameracode

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Matrix
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.util.Rational
import android.util.Size
import android.view.Surface
import android.view.TextureView
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import cc.gl.cameracode.aip.*
import cc.gl.cameracode.bean.DetectInfo
import cc.gl.cameracode.bean.Face
import cc.gl.cameracode.bean.UserGetResult
import cc.gl.cameracode.bean.UserInfo
import cc.gl.cameracode.util.Base64Util
import com.blankj.utilcode.util.LogUtils
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.*


// This is an arbitrary number we are using to keep tab of the permission
// request. Where an app has multiple context for requesting permission,
// this can help differentiate the different contexts
private const val REQUEST_CODE_PERMISSIONS = 10

class MainActivity : AppCompatActivity(), LifecycleOwner {

    // This is an array of all the permission specified in the manifest
    private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add this at the end of onCreate function

        viewFinder = findViewById(R.id.view_finder)

        // Request camera permissions
        if (allPermissionsGranted()) {
            viewFinder.post { startCamera() }
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }

        // Every time the provided texture view changes, recompute layout
        viewFinder.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            updateTransform()
        }
    }

    // Add this after onCreate

    private lateinit var viewFinder: TextureView

    private fun startCamera() {

        // Create configuration object for the viewfinder use case
        val previewConfig = PreviewConfig.Builder().apply {
            setTargetAspectRatio(Rational(1, 1))
            setTargetResolution(Size(640, 640))
            setLensFacing(CameraX.LensFacing.FRONT)
        }.build()

        // Build the viewfinder use case
        val preview = Preview(previewConfig)

        // Every time the viewfinder is updated, recompute layout
        preview.setOnPreviewOutputUpdateListener {

            // To update the SurfaceTexture, we have to remove it and re-add it
            val parent = viewFinder.parent as ViewGroup
            parent.removeView(viewFinder)
            parent.addView(viewFinder, 0)

            viewFinder.surfaceTexture = it.surfaceTexture
            updateTransform()
        }

        // Create configuration object for the image capture use case
        val imageCaptureConfig = ImageCaptureConfig.Builder()
            .apply {
                setTargetAspectRatio(Rational(1, 1))
                // We don't set a resolution for image capture; instead, we
                // select a capture mode which will infer the appropriate
                // resolution based on aspect ration and requested mode
                setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY)
                setLensFacing(CameraX.LensFacing.FRONT)
            }.build()

        // Build the image capture use case and attach button click listener
        val imageCapture = ImageCapture(imageCaptureConfig)
        findViewById<ImageButton>(R.id.capture_button).setOnClickListener {
            val file = File(
                externalCacheDir?.absolutePath,
                "${System.currentTimeMillis()}.jpg"
            )
            imageCapture.takePicture(file,
                object : ImageCapture.OnImageSavedListener {
                    override fun onError(
                        error: ImageCapture.UseCaseError,
                        message: String, exc: Throwable?
                    ) {
                        val msg = "Photo capture failed: $message"
                        Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                        Log.e("CameraXApp", msg)
                        exc?.printStackTrace()
                    }

                    override fun onImageSaved(file: File) {
                        val msg = "Photo capture succeeded: ${file.absolutePath}"
                        Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                        Log.d("CameraXApp", msg)

                        val disposable = io.reactivex.Observable.just(file)
                            .map {
                                val size = file.length().toInt()
                                val bytes = ByteArray(size)
                                try {
                                    val buf = BufferedInputStream(FileInputStream(file))
                                    buf.read(bytes, 0, bytes.size)
                                    val imageBase64 = Base64Util.encode(bytes)
                                    buf.close()

                                    return@map imageBase64
                                } catch (e: FileNotFoundException) {
                                    e.printStackTrace()
                                    return@map Constants.RX_ERROR
                                } catch (e: IOException) {
                                    e.printStackTrace()
                                    return@map Constants.RX_ERROR
                                }
                            }
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe { t ->
                                if (Constants.RX_ERROR == t) {
                                    LogUtils.eTag(Constants.TAG, t)
                                } else {
                                    detectFace(t)
                                    //addFace(t)
                                    //searchFace(t)
                                }
                            }

                        GroupGetUsers.getUsers("shenma")
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(object : Observer<Any?> {
                                override fun onComplete() {
                                }

                                override fun onSubscribe(d: Disposable) {
                                }

                                override fun onNext(t: Any) {
                                    if (Constants.RX_ERROR == t) {
                                        LogUtils.eTag(Constants.TAG, t)
                                    } else {
                                        LogUtils.eTag(Constants.TAG, t)
                                    }
                                }

                                override fun onError(e: Throwable) {
                                    LogUtils.eTag(Constants.TAG, e.message)
                                }
                            })
                    }
                })
        }

        // Setup image analysis pipeline that computes average pixel luminance
        val analyzerConfig = ImageAnalysisConfig.Builder().apply {
            // Use a worker thread for image analysis to prevent glitches
            val analyzerThread = HandlerThread(
                "LuminosityAnalysis"
            ).apply { start() }
            setCallbackHandler(Handler(analyzerThread.looper))
            // In our analysis, we care more about the latest image than
            // analyzing *every* image
            setImageReaderMode(
                ImageAnalysis.ImageReaderMode.ACQUIRE_LATEST_IMAGE
            )
            setLensFacing(CameraX.LensFacing.FRONT)
        }.build()

        // Build the image analysis use case and instantiate our analyzer
        val analyzerUseCase = ImageAnalysis(analyzerConfig).apply {
            analyzer = LuminosityAnalyzer()
        }

        // Bind use cases to lifecycle
        // If Android Studio complains about "this" being not a LifecycleOwner
        // try rebuilding the project or updating the appcompat dependency to
        // version 1.1.0 or higher.
        CameraX.bindToLifecycle(this, preview, imageCapture, analyzerUseCase)
    }

    private fun updateTransform() {
        val matrix = Matrix()

        // Compute the center of the view finder
        val centerX = viewFinder.width / 2f
        val centerY = viewFinder.height / 2f

        // Correct preview output to account for display rotation
        val rotationDegrees = when (viewFinder.display.rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> return
        }
        matrix.postRotate(-rotationDegrees.toFloat(), centerX, centerY)

        // Finally, apply transformations to our TextureView
        viewFinder.setTransform(matrix)
    }

    /**
     * Process result from permission request dialog box, has the request
     * been granted? If yes, start Camera. Otherwise display a toast
     */
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                viewFinder.post { startCamera() }
            } else {
                Toast.makeText(
                    this,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    /**
     * Check if all permission specified in the manifest have been granted
     */
    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun detectFace(imageBase64: String) {
        var face: Face? = null
        var userGetResult: UserGetResult? = null

        val disposable = FaceDetect.detect(imageBase64)
            .map {
                if (it is String) {
                    return@map Constants.RX_ERROR
                } else {
                    if(it is DetectInfo && it.face_list.isNotEmpty()) {
                        face = it.face_list[0]
                    }
                    return@map FaceSearch.search(imageBase64)
                }
            }
            .map { r ->
                when (r) {
                    is String -> if (r == Constants.USER_NOT_FOUND) {
                        if (face != null) {
                            //如果数据库中没有相关人员信息，将此用户数据保存到数据库

                            val userInfo = UserInfo(face!!.age,
                                face!!.beauty, face!!.face_token, face!!.gender)

                            FaceAdd.add(imageBase64, userInfo)

                            return@map userInfo
                        } else {
                            return@map Constants.RX_ERROR
                        }
                    } else {
                        return@map Constants.RX_ERROR
                    }
                    else -> (r as UserGetResult).user_list?.let {
                        if (it.isNotEmpty()) {
                            //如果数据库中有用户数据，那么返回用户数据
                            val userInfo = UserInfo(face!!.age,
                                face!!.beauty, face!!.face_token, face!!.gender)

                            return@map userInfo
                        }
                    }
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Any?> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Any) {

                    when(t) {
                        is String -> {
                            LogUtils.eTag(Constants.TAG, t)
                        }

                        is UserInfo -> {
                            LogUtils.eTag(Constants.TAG, t.toString())
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    LogUtils.eTag(Constants.TAG, e.message)
                }
            })
    }

    fun showUserInfo() {

    }
}
