package com.mamaliang.npps

import com.mamaliang.npps.cas.server.verticle.CasServerVerticle
import com.mamaliang.npps.common.RedisClient
import io.vertx.ext.web.Route
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.coAwait
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainVerticle : CoroutineVerticle() {

    override suspend fun start() {
        RedisClient.initialize(vertx)
        vertx.deployVerticle(CasServerVerticle::class.java.name).coAwait()
    }

}

// 扩展函数：将 handler 转换为 coroutine handler
@OptIn(DelicateCoroutinesApi::class)
fun Route.coroutineHandler(fn: suspend (RoutingContext) -> Unit) {
    handler { ctx ->
        GlobalScope.launch(ctx.vertx().dispatcher()) {
            try {
                fn(ctx)
            } catch (e: Exception) {
                ctx.fail(e)
            }
        }
    }
}


