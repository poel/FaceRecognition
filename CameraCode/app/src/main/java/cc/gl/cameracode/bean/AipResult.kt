package cc.gl.cameracode.bean

class AipResult<T>(val error_code: Int, val error_msg: String, val log_id: Long, val timestamp:Long, val cached: Int, val result: T)