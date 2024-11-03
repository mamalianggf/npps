package com.mamaliang.npps.manage.handler;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

/**
 * @author gaof
 * @date 2024/10/28
 */
public class HealthHandler implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext routingContext) {
        JsonObject content = JsonObject.of("status", "UP");
        routingContext.request().response()
                .end(content.encode());
    }
}
