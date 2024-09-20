package com.mamaliang.npps.cas.server.ticket;

import java.time.ZonedDateTime;

/**
 * ticket通用概念接口
 */
public interface Ticket {

  /**
   * 获取ticket的id
   *
   * @return ticket的id
   */
  String getId();

  /**
   * 获取ticket的前缀
   *
   * @return ticket的前缀
   */
  String getPrefix();

  /**
   * 获取ticket的使用次数
   *
   * @return ticket的使用次数
   */
  int getCountOfUses();

  /**
   * 获取ticket的最后使用时间
   *
   * @return ticket的最后使用时间
   */
  ZonedDateTime getLastTimeUsed();

  /**
   * 获取ticket的倒数第二次使用时间
   *
   * @return ticket的倒数第二次使用时间
   */

  ZonedDateTime getPreviousTimeUsed();

  /**
   * 获取ticket的创建时间
   *
   * @return ticket的创建时间
   */
  ZonedDateTime getCreationTime();

  /**
   * 通常情况下需更新一下内容
   * 1. 倒数第二次使用时间
   * 2. 最后使用时间
   * 3. 使用次数
   */
  void update();

  /**
   * 是否过期，大多数的实现和ExpirationPolicy相关联
   *
   * @return 如果过期返回true
   * @see ExpirationPolicy
   */
  boolean isExpired();

  /**
   * 获取ticket的过期策略
   *
   * @return ticket的过期策略
   */
  ExpirationPolicy getExpirationPolicy();

}
