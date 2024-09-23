package com.mamaliang.npps.cas.server.authentication.handler

import com.mamaliang.npps.cas.server.authentication.credential.Credential
import java.security.GeneralSecurityException

/**
 * 认证处理类
 *
 * @author gaof
 * @date 2024/9/5
 */
interface AuthenticationHandler {
    /**
     * 值越小，优先级越高
     */
    val order: Int

    @Throws(GeneralSecurityException::class)
    suspend fun authenticate(credential: Credential): AuthenticationHandlerResult

    fun supports(credential: Credential): Boolean

    companion object {
        val HIGHEST_PRECEDENCE: Int = Int.Companion.MIN_VALUE
        val LOWEST_PRECEDENCE: Int = Int.Companion.MAX_VALUE
    }

}
