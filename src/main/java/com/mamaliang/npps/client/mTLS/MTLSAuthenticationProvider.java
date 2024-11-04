package com.mamaliang.npps.client.mTLS;

import com.mamaliang.npps.common.CustomAuthenticationProvider;
import com.mamaliang.npps.common.Tuple;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.auth.User;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.openssl.PEMParser;

import java.io.IOException;
import java.io.StringReader;

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
        String remoteAddr = request.remoteAddress().host();
        if (!"127.0.0.1".equals(remoteAddr)) {
            return new Tuple<>(false, null);
        }
        String cert = request.getParam("cert");
        String sid = request.getParam("sid");
        String timeout = request.getHeader("Custom-Session-TimeOut");
        if (StringUtils.isBlank(timeout)) {
            timeout = request.getHeader("TRP-SSL-Session");
        }
        if (StringUtils.isAnyBlank(cert, sid, timeout)) {
            return new Tuple<>(false, null);
        }
        return new Tuple<>(true, new MTLSCredentials(cert, sid, Integer.parseInt(timeout)));
    }


    @Override
    public Future<User> authenticate(MTLSCredentials credentials) {
        try (StringReader stringReader = new StringReader(credentials.getCertPem());
             PEMParser pemParser = new PEMParser(stringReader)) {
            X509CertificateHolder holder = (X509CertificateHolder) pemParser.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // SessionType.VERIFY_CLIENT
        return null;
    }
}
