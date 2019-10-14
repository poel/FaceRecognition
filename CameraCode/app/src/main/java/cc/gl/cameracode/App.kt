package cc.gl.cameracode

import android.app.Application
import com.blankj.utilcode.util.Utils
import io.reactivex.schedulers.Schedulers

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        Utils.init(this)

        Schedulers.io().scheduleDirect {
            AuthService.getAuth(Constants.AIP_API_KEY, Constants.AIP_SECRET_KEY)
        }
    }
}