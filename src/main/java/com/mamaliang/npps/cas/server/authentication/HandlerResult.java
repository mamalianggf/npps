package com.mamaliang.npps.cas.server.authentication;

import com.mamaliang.npps.cas.server.authentication.principal.Principal;

/**
 * authentication handler result
 */
public interface HandlerResult {

  /**
   * 获取handler name.
   *
   * @return handler name
   */
  String getHandlerName();

  /**
   * 获取credential.
   *
   * @return credential
   */
  Credential getCredential();

  /**
   * 获取principal.
   *
   * @return principal
   */
  Principal getPrincipal();
}
