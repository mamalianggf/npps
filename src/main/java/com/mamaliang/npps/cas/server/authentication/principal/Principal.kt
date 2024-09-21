package com.mamaliang.npps.cas.server.authentication.principal

/**
 * Generic concept of an authenticated thing. Examples include a person or a service
 * 通常代表经过身份验证的用户
 */
interface Principal {
  /**
   * 获取principal的唯一描述符
   *
   * @return principal的唯一描述符
   */
  fun getId(): String

  /**
   * 获取principal的属性
   *
   * @return principal的属性
   */
  fun getAttributes(): MutableMap<String, Any> {
    return LinkedHashMap<String, Any>(0)
  }
}
