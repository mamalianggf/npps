package com.mamaliang.npps.cas.server.authentication;

/**
 * an authentication credential
 *
 * @author gaof
 * @date 2024/9/5
 */
public interface Credential {

  /**
   * Credential的标识符类如 username、userId
   * 可用于记录日志、审计等
   */
  String getId();

}
