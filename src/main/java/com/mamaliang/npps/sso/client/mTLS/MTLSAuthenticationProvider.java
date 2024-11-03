package com.mamaliang.npps.sso.client.mTLS;

import com.mamaliang.npps.sso.common.CustomAuthenticationProvider;
import com.mamaliang.npps.common.Tuple;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.auth.User;

/**
 * 双向认证非传统会话
 * GET /login?cert=xxx&sid=xxx
 * headers:
 * App-Id: xxx
 * X-Real-IP: xxx
 * TRP-SSL-Session: xxx
 * User-Agent: xxx
 * Custom-Session-TimeOut: xxx
 * Add-Max-Age-Ua-Types: xxx
 *
 * @author gaof
 * @date 2024/10/30
 */
public class MTLSAuthenticationProvider implements CustomAuthenticationProvider<MTLSCredentials> {

    @Override
    public Tuple<Boolean, MTLSCredentials> support(HttpServerRequest request) {
        // 限制IP 只能本地调用

        // request.getParameter("cert");
        return null;
    }


    @Override
    public Future<User> authenticate(MTLSCredentials credentials) {
        return null;
    }
}
