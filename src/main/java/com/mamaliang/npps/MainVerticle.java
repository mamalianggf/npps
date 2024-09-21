package com.mamaliang.npps;

import com.mamaliang.npps.objects.RedisClientSingleton;
import io.vertx.core.AbstractVerticle;

/**
 * @author gaof
 * @date 2024/8/30
 */
public class MainVerticle extends AbstractVerticle {

  @Override
  public void start() {
    RedisClientSingleton.initialize(vertx);
    vertx.deployVerticle("com.mamaliang.npps.cas.server.TgtCoroutineVerticle");
  }


}
