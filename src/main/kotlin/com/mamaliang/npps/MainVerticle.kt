package com.mamaliang.npps

import com.mamaliang.npps.common.RedisClient
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.coAwait

class MainVerticle: CoroutineVerticle() {

  override suspend fun start() {
    RedisClient.initialize(vertx)
    vertx.deployVerticle("com.mamaliang.npps.cas.server.CasServerVerticle").coAwait()
  }
}
