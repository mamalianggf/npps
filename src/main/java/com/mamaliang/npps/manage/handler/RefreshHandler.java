package com.mamaliang.npps.manage.handler;

import com.mamaliang.npps.client.ClientVerticle;
import com.mamaliang.npps.sso.SsoVerticle;
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

    private String ssoId;

    private String clientId;

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
        DeploymentOptions ssoOptions = new DeploymentOptions();
        ssoOptions.setInstances(1);
        ssoOptions.setThreadingModel(ThreadingModel.VIRTUAL_THREAD);
        this.ssoId = Future.await(vertx.deployVerticle(SsoVerticle.class, ssoOptions));

        DeploymentOptions clientOptions = new DeploymentOptions();
        clientOptions.setInstances(1);
        clientOptions.setThreadingModel(ThreadingModel.VIRTUAL_THREAD);
        this.clientId = Future.await(vertx.deployVerticle(ClientVerticle.class, clientOptions));

        DeploymentOptions portalOptions = new DeploymentOptions();
        portalOptions.setInstances(1);
        portalOptions.setThreadingModel(ThreadingModel.VIRTUAL_THREAD);
        this.portalId = Future.await(vertx.deployVerticle(PortalVerticle.class, portalOptions));
    }

    public void undeploy() {
        if (Objects.nonNull(ssoId)) {
            Future.await(vertx.undeploy(ssoId));
        }
        if (Objects.nonNull(clientId)) {
            Future.await(vertx.undeploy(clientId));
        }
        if (Objects.nonNull(portalId)) {
            Future.await(vertx.undeploy(portalId));
        }
    }

}
