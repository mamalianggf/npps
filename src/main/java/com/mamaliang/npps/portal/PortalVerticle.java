package com.mamaliang.npps.portal;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class PortalVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) {

        vertx.createHttpServer().requestHandler(req -> req.response()
                        .putHeader("content-type", "text/plain")
                        .end("portal todo"))
                .listen(60500).onComplete(http -> {
                    if (http.succeeded()) {
                        startPromise.complete();
                        System.out.println("Portal server started on port 60500.");
                    } else {
                        startPromise.fail(http.cause());
                    }
                });
    }
}
