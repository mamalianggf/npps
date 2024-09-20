package com.mamaliang.npps.cas.server

import io.vertx.core.AsyncResult
import io.vertx.core.Promise
import io.vertx.core.http.HttpServer
import io.vertx.ext.web.Router
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.coroutineRouter
import lombok.extern.slf4j.Slf4j

/**
 * @author gaof
 * @date 2024/9/6
 */
@Slf4j
class TgtCoroutineVerticle : CoroutineVerticle() {

  override fun start(startFuture: Promise<Void>?) {

    val router = Router.router(vertx)
    coroutineRouter {
        // Route.coRespond is similar to Route.respond but using a suspending function
      router.get("/sso/login").coHandler {  }.coRespond {
        // similar to Route.respond but using a suspending function
        val response = "hello world 8081"
        response // sent by Vert.x to the client
      }
    }

    val server = vertx.createHttpServer()
    server.requestHandler(router)
      .listen(8081)
      .onComplete { http: AsyncResult<HttpServer?> ->
        if (http.succeeded()) {
          startFuture?.complete()
        } else {
          startFuture?.fail(http.cause())
        }
      }
  }
}
