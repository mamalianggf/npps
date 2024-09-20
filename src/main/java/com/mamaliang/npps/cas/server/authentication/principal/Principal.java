package com.mamaliang.npps.cas.server.authentication.principal;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Generic concept of an authenticated thing. Examples include a person or a service
 * 通常代表经过身份验证的用户
 */
public interface Principal {

  /**
   * 获取principal的唯一描述符
   *
   * @return principal的唯一描述符
   */
  String getId();

  /**
   * 获取principal的属性
   *
   * @return principal的属性
   */
  default Map<String, Object> getAttributes() {
    return new LinkedHashMap<>(0);
  }
}
