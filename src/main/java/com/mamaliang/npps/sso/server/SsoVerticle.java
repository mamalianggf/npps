package com.mamaliang.npps.sso.server;

import com.mamaliang.npps.sso.server.handler.LoginHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * @author gaof
 * @date 2024/10/25
 */
public class SsoVerticle extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) {

        WebClient webClient = initWebClient(vertx);

        Router subRouter = Router.router(vertx);
        subRouter.post("/login").handler(new LoginHandler(webClient));

        Router mainRouter = Router.router(vertx);
        mainRouter.route("/v1/sso/*")
                .consumes("application/json; charset=utf-8")
                .produces("application/json; charset=utf-8")
                .handler(BodyHandler.create())
                .subRouter(subRouter);

        vertx.createHttpServer().requestHandler(mainRouter).
                listen(60500).onComplete(http -> {
                    if (http.succeeded()) {
                        startPromise.complete();
                        System.out.println("Cas server started on port 60500.");
                    } else {
                        startPromise.fail(http.cause());
                    }
                });
    }


    private WebClient initWebClient(Vertx vertx) {
        WebClientOptions webClientOptions = new WebClientOptions()
                .setUserAgent("NPPS");
        return WebClient.create(vertx, webClientOptions);
    }

}
