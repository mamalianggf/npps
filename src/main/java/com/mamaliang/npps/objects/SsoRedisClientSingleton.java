package com.mamaliang.npps.objects;

import io.vertx.core.Vertx;
import io.vertx.redis.client.Redis;
import io.vertx.redis.client.RedisAPI;
import io.vertx.redis.client.RedisOptions;

public class SsoRedisClientSingleton {

  private static RedisAPI redisApi;

  private SsoRedisClientSingleton() {
  }

  public static void initialize(Vertx vertx) {
    if (redisApi == null) {
      RedisOptions options = new RedisOptions()
        .setConnectionString("redis://10.0.209.174:6379/0");
      Redis redisClient = Redis.createClient(vertx, options);
      redisApi = RedisAPI.api(redisClient);
    }
  }

  // 提供全局访问点
  public static RedisAPI getInstance() {
    if (redisApi == null) {
      throw new IllegalStateException("SsoRedisClientSingleton is not initialized. Call initialize() first.");
    }
    return redisApi;
  }
}

