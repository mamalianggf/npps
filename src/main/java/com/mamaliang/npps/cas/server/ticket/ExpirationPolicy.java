package com.mamaliang.npps.cas.server.ticket;

/**
 * ticket过期策略接口
 */
public interface ExpirationPolicy {

  /**
   * 获取ticket允许的最大存活时间
   *
   * @return 最大存活时间，单位秒
   */
  Long getTimeToLive();

  /**
   * 获取ticket允许的最大空闲时间
   *
   * @return 最大空闲时间，单位秒
   */
  Long getTimeToIdle();

  /**
   * 获取过期策略名称
   *
   * @return 名称
   */
  String getName();
}
