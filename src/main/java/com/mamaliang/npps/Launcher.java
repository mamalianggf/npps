package com.mamaliang.npps;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.ThreadingModel;

/**
 * @author gaof
 * @date 2024/10/29
 */
public class Launcher extends io.vertx.core.Launcher {

    public static void main(String[] args) {
        new Launcher().dispatch(args);
    }

    @Override
    public void beforeDeployingVerticle(DeploymentOptions deploymentOptions) {
        deploymentOptions.setThreadingModel(ThreadingModel.VIRTUAL_THREAD);
    }
}
