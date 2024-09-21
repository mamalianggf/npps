package com.mamaliang.npps.objects;

import io.vertx.core.Vertx;
import io.vertx.redis.client.Redis;
import io.vertx.redis.client.RedisOptions;

public class RedisClientSingleton {

  private static Redis casServerRedisClient;
  private static Redis casClientRedisClient;

  private RedisClientSingleton() {
  }

  public static void initialize(Vertx vertx) {
    if (casClientRedisClient == null) {
      RedisOptions options = new RedisOptions()
        .setConnectionString("redis://10.0.209.174:6379/0");
      casClientRedisClient = Redis.createClient(vertx, options);
    }
    if (casServerRedisClient == null) {
      RedisOptions options = new RedisOptions()
        .setConnectionString("redis://10.0.209.174:6379/1");
      casServerRedisClient = Redis.createClient(vertx, options);
    }
  }

  public static Redis getCasClientRedisClient() {
    if (casClientRedisClient == null) {
      throw new IllegalStateException("RedisClientSingleton is not initialized. Call initialize() first.");
    }
    return casClientRedisClient;
  }

  public static Redis getCasServerRedisClient() {
    if (casServerRedisClient == null) {
      throw new IllegalStateException("RedisClientSingleton is not initialized. Call initialize() first.");
    }
    return casServerRedisClient;
  }
}

