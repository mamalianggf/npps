package com.mamaliang.npps.cas.server

import io.vertx.ext.web.Router
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.coAwait

/**
 * @author gaof
 * @date 2024/9/6
 */
class CasServerVerticle : CoroutineVerticle() {

  override suspend fun start() {
    val router = Router.router(vertx)
    router.get("/sso/login").handler {  }

    vertx.createHttpServer()
      .requestHandler(router)
      .listen(8080)
      .coAwait()
  }
}
