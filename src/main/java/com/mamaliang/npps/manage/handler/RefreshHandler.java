package com.mamaliang.npps.manage.handler;

import com.mamaliang.npps.sso.client.CasClientVerticle;
import com.mamaliang.npps.sso.server.SsoVerticle;
import com.mamaliang.npps.portal.PortalVerticle;
import io.vertx.core.*;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.Objects;

/**
 * @author gaof
 * @date 2024/10/28
 */
public class RefreshHandler implements Handler<RoutingContext> {

    private final Vertx vertx;

    private String casServerId;

    private String casClientId;

    private String portalId;

    public RefreshHandler(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public void handle(RoutingContext routingContext) {
        undeploy();
        deploy();
        JsonObject content = JsonObject.of("status", "ok");
        routingContext.request().response()
                .end(content.encode());
    }

    public void deploy() {
        DeploymentOptions casServerOptions = new DeploymentOptions();
        casServerOptions.setInstances(1);
        casServerOptions.setThreadingModel(ThreadingModel.VIRTUAL_THREAD);
        this.casServerId = Future.await(vertx.deployVerticle(SsoVerticle.class, casServerOptions));

        DeploymentOptions casClientOptions = new DeploymentOptions();
        casClientOptions.setInstances(1);
        casClientOptions.setThreadingModel(ThreadingModel.VIRTUAL_THREAD);
        this.casClientId = Future.await(vertx.deployVerticle(CasClientVerticle.class, casClientOptions));

        DeploymentOptions portalOptions = new DeploymentOptions();
        portalOptions.setInstances(1);
        portalOptions.setThreadingModel(ThreadingModel.VIRTUAL_THREAD);
        this.portalId = Future.await(vertx.deployVerticle(PortalVerticle.class, portalOptions));
    }

    public void undeploy() {
        if (Objects.nonNull(casServerId)) {
            Future.await(vertx.undeploy(casServerId));
        }
        if (Objects.nonNull(casClientId)) {
            Future.await(vertx.undeploy(casClientId));
        }
        if (Objects.nonNull(portalId)) {
            Future.await(vertx.undeploy(portalId));
        }
    }

}
