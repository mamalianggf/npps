package com.mamaliang.npps;

import io.vertx.core.AbstractVerticle;

/**
 * @author gaof
 * @date 2024/8/30
 */
public class MainVerticle extends AbstractVerticle {

  @Override
  public void start() {
    vertx.deployVerticle("com.mamaliang.npps.cas.server.TgtCoroutineVerticle");
  }


}
