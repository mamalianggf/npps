package com.mamaliang.npps.cas.server.authentication

import com.mamaliang.npps.common.Ordered
import java.security.GeneralSecurityException

/**
 * 认证处理类
 *
 * @author gaof
 * @date 2024/9/5
 */
interface AuthenticationHandler : Ordered {

    val name: String

    @Throws(GeneralSecurityException::class)
    fun authenticate(credential: Credential): AuthenticationHandlerResult

    fun supports(credential: Credential): Boolean

}
