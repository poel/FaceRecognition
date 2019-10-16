package cc.gl.cameracode.aip

import cc.gl.cameracode.Constants
import cc.gl.cameracode.bean.AipResult
import cc.gl.cameracode.bean.DetectInfo
import cc.gl.cameracode.util.HttpUtil
import com.blankj.utilcode.util.GsonUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable

/**
 * 人脸检测与属性分析
 */
object FaceDetect {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    fun detect(imageStr: String): Observable<Any> {
        return Observable.create {
            // 请求url
            val url = "https://aip.baidubce.com/rest/2.0/face/v3/detect"
            try {
                val map: HashMap<String, String> = HashMap()
                map.put("image", imageStr)
                map.put("face_field", "age,beauty,expression,face_shape,gender,glasses,landmark,landmark150,race,quality,eye_status,emotion,face_type")
                map.put("image_type", "BASE64")

                val param = GsonUtils.toJson(map)

                // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
                val accessToken = Constants.AIP_ACCESS_TOKEN

                val result = HttpUtil.post(url, accessToken, "application/json", param)

                val type = object : TypeToken<AipResult<DetectInfo>>() {}.type

                val response: AipResult<DetectInfo> = Gson().fromJson<AipResult<DetectInfo>>(result, type)

                if(response.error_code != Constants.SUCCESS_CODE) {
                    it.onError(Throwable(response.error_msg))
                }else {
                    it.onNext(response.result)
                }

            } catch (e: Exception) {
                e.printStackTrace()
                it.onNext(Constants.RX_ERROR)
            }
        }
    }
}