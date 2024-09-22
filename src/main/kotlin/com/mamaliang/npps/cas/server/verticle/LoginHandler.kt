package com.mamaliang.npps.cas.server.verticle

import io.vertx.ext.web.RoutingContext

/**
 * @author gaof
 * @date 2024/9/22
 */
class LoginHandler {
    suspend fun handle(ctx: RoutingContext) {
        ctx.response().end("hello world")
    }
}