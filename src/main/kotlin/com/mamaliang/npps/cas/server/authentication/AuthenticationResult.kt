package com.mamaliang.npps.cas.server.authentication

import java.io.Serializable

/**
 * authentication result
 */
interface AuthenticationResult : Serializable {
    /**
     * 获取authentication
     *
     * @return authentication
     */
    val authentication: Authentication

    /**
     * 获取service
     *
     * @return service
     */
    val service: Service
}
