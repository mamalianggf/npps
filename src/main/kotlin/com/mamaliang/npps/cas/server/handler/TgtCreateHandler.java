package com.mamaliang.npps.cas.server.handler;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import io.vertx.redis.client.Redis;
import io.vertx.redis.client.RedisAPI;
import lombok.extern.slf4j.Slf4j;

/**
 * @author gaof
 * @date 2024/8/30
 */
@Slf4j
public class TgtCreateHandler implements Handler<RoutingContext> {
  @Override
  public void handle(RoutingContext event) {

    Redis client = Redis.createClient(event.vertx(), "redis://10.0.209.174:6379/0");
    RedisAPI api = RedisAPI.api(client);
    api.setnx("tgt1", "111")
      .onFailure(i -> {
        log.error("redis set tgt failed: {}", i.getMessage());
      })
      .onSuccess(i -> {
        log.info("redis set tgt success");
        HttpServerResponse resp = event.response();
        resp.end("tgt created ");
      });
  }
}
