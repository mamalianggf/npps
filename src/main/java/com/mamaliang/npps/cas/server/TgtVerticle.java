package com.mamaliang.npps.cas.server;

import com.mamaliang.npps.cas.server.handler.TgtCreateHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import lombok.extern.slf4j.Slf4j;

/**
 * @author gaof
 * @date 2024/8/29
 */
@Slf4j
public class TgtVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) {
    Router router = Router.router(vertx);
    router.get("/sso/login").handler(new TgtCreateHandler());

    HttpServer server = vertx.createHttpServer();
    server.requestHandler(router)
      .listen(8080)
      .onComplete(http -> {
        if (http.succeeded()) {
          log.info("HTTP server started on port 8080");
          startPromise.complete();
        } else {
          log.error("HTTP server started failed");
          startPromise.fail(http.cause());
        }
      });
  }
}
