package com.mamaliang.npps.cas.server.authentication.handler

import com.mamaliang.npps.cas.server.authentication.credential.Credential
import com.mamaliang.npps.cas.server.authentication.principal.Principal

/**
 * 认证处理类返回的结果
 */
class AuthenticationHandlerResult(
    val credential: Credential,
    val principal: Principal
) {
    val SUCCESSFUL_AUTHENTICATION_HANDLERS: String = "successfulAuthenticationHandlers"
}
