package com.mamaliang.npps.cas.server.authentication

import java.time.ZonedDateTime

interface Authentication {

  val principal: Principal

  val authenticationDate: ZonedDateTime

  /**
   * 此处的属性非Principal的属性
   */
  val attributes: Map<String, Any>

  val successes: Map<String, AuthenticationHandlerResult>

  val failures: Map<String, Class<out Throwable>>

  fun addAttribute(name: String, value: Any)

  /**
   * 如果key冲突，不进行覆盖
   */
  fun update(authn: Authentication)

  /**
   * 如果key冲突，进行覆盖
   */
  fun updateAll(authn: Authentication)
}
