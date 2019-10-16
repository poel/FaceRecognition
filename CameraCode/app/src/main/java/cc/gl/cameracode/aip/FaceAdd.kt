package cc.gl.cameracode.aip

import cc.gl.cameracode.Constants
import cc.gl.cameracode.bean.AddFaceReponse
import cc.gl.cameracode.bean.AipResult
import cc.gl.cameracode.bean.UserInfo
import cc.gl.cameracode.util.GsonUtils
import cc.gl.cameracode.util.HttpUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * 人脸注册
 */
object FaceAdd {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    fun add(imageStr: String, userInfo: UserInfo): Any {
        // 请求url
        val url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add"
        try {
            val map: HashMap<String, String> = HashMap()
            map.put("image", imageStr)
            map.put("group_id", "shenma")
            map.put("user_id", "user1")
            map.put("user_info", GsonUtils.toJson(userInfo))
            map.put("liveness_control", "NORMAL")
            map.put("image_type", "BASE64")
            map.put("quality_control", "NONE")

            val param: String = GsonUtils.toJson(map)

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            val accessToken = Constants.AIP_ACCESS_TOKEN

            val result = HttpUtil.post(url, accessToken, "application/json", param)

            val type = object : TypeToken<AipResult<AddFaceReponse>>() {}.type

            val response: AipResult<AddFaceReponse> = Gson().fromJson<AipResult<AddFaceReponse>>(result, type)

            return if(response.error_code == Constants.SUCCESS_CODE) {
                response.result
            }else {
                Constants.RX_ERROR
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return Constants.RX_ERROR
    }
}