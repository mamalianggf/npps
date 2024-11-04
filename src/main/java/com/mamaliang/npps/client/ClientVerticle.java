package com.mamaliang.npps.client;

import com.mamaliang.npps.client.handler.LoginHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;

/**
 * @author gaof
 * @date 2024/10/25
 */
public class ClientVerticle extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) {

        Router router = Router.router(vertx);
        router.route("/login").handler(new LoginHandler());
        vertx.createHttpServer().requestHandler(router).
                listen(60500).onComplete(http -> {
                    if (http.succeeded()) {
                        startPromise.complete();
                        System.out.println("Cas client started on port 60500.");
                    } else {
                        startPromise.fail(http.cause());
                    }
                });
    }

}
