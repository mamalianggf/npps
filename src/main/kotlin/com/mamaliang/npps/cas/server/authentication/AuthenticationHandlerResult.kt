package com.mamaliang.npps.cas.server.authentication

/**
 * 认证处理类返回的结果
 */
interface AuthenticationHandlerResult {
    /**
     * 获取handler name.
     *
     * @return handler name
     */
    val handlerName: String

    /**
     * 获取credential.
     *
     * @return credential
     */
    val credential: Credential

    /**
     * 获取principal.
     *
     * @return principal
     */
    val principal: Principal
}
