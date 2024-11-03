package com.mamaliang.npps.sso.client.mTLS;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.authentication.Credentials;

/**
 * @author gaof
 * @date 2024/10/30
 */
public class MTLSCredentials implements Credentials {

    // String certPem, String sessionId, int sessionTimeout

    @Override
    public JsonObject toJson() {
        return null;
    }
}
