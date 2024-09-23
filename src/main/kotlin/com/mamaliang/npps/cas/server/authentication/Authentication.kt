package com.mamaliang.npps.cas.server.authentication

import com.mamaliang.npps.cas.server.authentication.handler.AuthenticationHandlerResult
import com.mamaliang.npps.cas.server.authentication.principal.Principal
import java.time.ZonedDateTime

class Authentication(
    val principal: Principal,
    val authenticationDate: ZonedDateTime,
    /**
     * 此处的属性非Principal的属性
     */
    val attributes: Map<String, Any>,
    val successes: Map<String, AuthenticationHandlerResult>,
    val failures: Map<String, Class<out Throwable>>
) {
    /**
     * 如果key冲突，不进行覆盖
     */
    fun update(authn: Authentication) {

    }

    /**
     * 如果key冲突，进行覆盖
     */
    fun updateAll(authn: Authentication) {

    }
}
