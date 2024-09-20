package com.mamaliang.npps.common;

public interface Ordered {

  int HIGHEST_PRECEDENCE = Integer.MIN_VALUE;

  int LOWEST_PRECEDENCE = Integer.MAX_VALUE;

  /**
   * 值越小，优先级越高
   */
  int getOrder();

}
