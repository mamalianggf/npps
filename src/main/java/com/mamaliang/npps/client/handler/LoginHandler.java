package com.mamaliang.npps.client.handler;

import com.mamaliang.npps.client.session.UserSession;
import com.mamaliang.npps.common.CustomAuthenticationProvider;
import com.mamaliang.npps.common.Tuple;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authentication.Credentials;
import io.vertx.ext.web.RoutingContext;

import java.util.List;
import java.util.Objects;

/**
 * @author gaof
 * @date 2024/10/30
 */
public class LoginHandler implements Handler<RoutingContext> {

    private final List<CustomAuthenticationProvider<? extends Credentials>> providers;

    public LoginHandler() {
        providers = List.of();
    }

    @Override
    public void handle(RoutingContext routingContext) {
        HttpServerRequest req = routingContext.request();
        User user = null;
        for (CustomAuthenticationProvider<? extends Credentials> provider : providers) {
            Tuple<Boolean, User> result = processProvider(provider, req);
            if (result.v1()) {
                user = result.v2();
                break;
            }
        }
        if (Objects.isNull(user)){
            // response 401
        }
        UserSession userSession = constructUserSession(user);
        // redis add online user session


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

    private UserSession constructUserSession(User user) {
        return null;
    }
}
