package com.mamaliang.npps.common

/**
 * @author gaof
 * @date 2024/9/26
 */
sealed class RmsResponse<out T>

data class RmsSuccessResponse<T>(
    val data: T
) : RmsResponse<T>()

data class RmsErrorResponse(
    val error: Error
) : RmsResponse<Nothing>()

data class Error(
    val id: String,
    val code: Int,
    val message: String,
    val errors: List<Detail>
)

data class Detail(
    val domain: String,
    val message: String,
    val reason: String,
    val recoveryOptions: String,
    val diagnoseInfo: Map<String, Any>,
    val extendedHelper: String,
    val sendReport: String
)