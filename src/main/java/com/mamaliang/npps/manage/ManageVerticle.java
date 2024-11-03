package com.mamaliang.npps.manage;

import com.mamaliang.npps.manage.handler.HealthHandler;
import com.mamaliang.npps.manage.handler.RefreshHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;

public class ManageVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) {

        Router subRouter = Router.router(vertx);
        subRouter.get("/health").handler(new HealthHandler());

        RefreshHandler refreshHandler = new RefreshHandler(vertx);
        refreshHandler.deploy();
        subRouter.post("/refresh").handler(refreshHandler);

        Router mainRouter = Router.router(vertx);
        mainRouter.route("/v1/manage/actuator/*")
                .produces("application/json; charset=utf-8")
                .subRouter(subRouter);

        vertx.createHttpServer().requestHandler(mainRouter)
                .listen(60501).onComplete(http -> {
                    if (http.succeeded()) {
                        System.out.println("Manage server started on port 60501.");
                    } else {
                        startPromise.fail(http.cause());
                    }
                });

    }
}
