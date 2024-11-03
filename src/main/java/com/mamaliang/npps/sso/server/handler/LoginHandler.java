package com.mamaliang.npps.sso.server.handler;

import com.mamaliang.npps.sso.common.CustomAuthenticationProvider;
import com.mamaliang.npps.sso.server.provider.rms.RmsAuthenticationProvider;
import com.mamaliang.npps.common.Tuple;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authentication.Credentials;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.WebClient;

import java.util.List;

/**
 * @author gaof
 * @date 2024/10/25
 */
public class LoginHandler implements Handler<RoutingContext> {


    private final List<CustomAuthenticationProvider<? extends Credentials>> providers;

    public LoginHandler(WebClient webClient) {
        providers = List.of(new RmsAuthenticationProvider(webClient));
    }

    @Override
    public void handle(RoutingContext routingContext) {
        HttpServerRequest req = routingContext.request();
        User user;
        for (CustomAuthenticationProvider<? extends Credentials> provider : providers) {
            Tuple<Boolean, User> result = processProvider(provider, req);
            if (result.v1()) {
                user = result.v2();
                break;
            }
        }
    }

    private <T extends Credentials> Tuple<Boolean, User> processProvider(CustomAuthenticationProvider<T> provider, HttpServerRequest req) {
        Tuple<Boolean, T> result = provider.support(req);
        if (result.v1()) {
            T credentials = result.v2();
            User user = Future.await(provider.authenticate(credentials));
            return new Tuple<>(true, user);
        }
        return new Tuple<>(false, null);
    }

}