package com.mamaliang.npps.cas.server.verticle

import com.mamaliang.npps.coroutineHandler
import io.vertx.ext.web.Router
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.coAwait

/**
 * @author gaof
 * @date 2024/9/6
 */
class CasServerVerticle : CoroutineVerticle() {

    override suspend fun start() {
        val router = createRouter()
        vertx.createHttpServer()
            .requestHandler(router)
            .listen(8080)
            .coAwait()
    }

    private fun createRouter() = Router.router(vertx).apply {
        get("/sso/login").coroutineHandler { ctx -> LoginHandler().handle(ctx) }
    }

}




