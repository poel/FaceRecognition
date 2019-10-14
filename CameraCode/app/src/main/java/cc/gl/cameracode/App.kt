package cc.gl.cameracode

import android.app.Application
import com.blankj.utilcode.util.Utils
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        Utils.init(this)

        AuthService.getAuth(Constants.AIP_API_KEY, Constants.AIP_SECRET_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String?> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: String) {
                    if(Constants.RX_ERROR.equals(t)) {
                        Constants.AIP_ACCESS_TOKEN = null
                    }else {
                        Constants.AIP_ACCESS_TOKEN = t
                    }
                }

                override fun onError(e: Throwable) {
                }
            })

    }
}