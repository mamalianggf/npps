package com.mamaliang.npps.client.mTLS;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.authentication.Credentials;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author gaof
 * @date 2024/10/30
 */
@Getter
@Setter
@AllArgsConstructor
public class MTLSCredentials implements Credentials {

    private String certPem;
    private String sessionId;
    private int sessionTimeout;

    @Override
    public JsonObject toJson() {
        return null;
    }
}
