package com.mamaliang.npps.sso.common;

import com.mamaliang.npps.common.SupportType;
import com.mamaliang.npps.common.Tuple;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authentication.Credentials;

/**
 * @author gaof
 * @date 2024/10/25
 */
public interface CustomAuthenticationProvider<T extends Credentials> extends SupportType<HttpServerRequest, Tuple<Boolean, T>> {

    Future<User> authenticate(T credentials);

}
