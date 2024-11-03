package com.mamaliang.npps.sso.server.provider.rms;

import com.mamaliang.npps.sso.common.CustomAuthenticationProvider;
import com.mamaliang.npps.common.Tuple;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authentication.UsernamePasswordCredentials;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;

import java.util.Objects;

/**
 * @author gaof
 * @date 2024/10/25
 */
public class RmsAuthenticationProvider implements CustomAuthenticationProvider<UsernamePasswordCredentials> {

    private final WebClient webClient;

    public RmsAuthenticationProvider(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Future<User> authenticate(UsernamePasswordCredentials credentials) {
        JsonObject requestBody = new JsonObject();
        requestBody.put("loginName", credentials.getUsername());
        requestBody.put("password", credentials.getPassword());
        HttpResponse<JsonObject> response = Future.await(webClient.post("http://127.0.0.1:60700/users/login?withStatus=true")
                .as(BodyCodec.jsonObject())
                .sendJsonObject(requestBody)
        );
        if (response.statusCode() == 200 && Objects.nonNull(response.body().getJsonObject("data"))) {
            JsonObject userJsonObject = response.body().getJsonObject("data");
            JsonObject principal = new JsonObject();
            principal.put("username", credentials.getUsername());
            User user = User.create(principal, userJsonObject);
            return Future.succeededFuture(user);
        } else {
            return Future.failedFuture(String.format("Rms响应异常,响应码：%s,响应内容：%s", response.statusCode(), response.body()));
        }
    }

    @Override
    public Tuple<Boolean, UsernamePasswordCredentials> support(HttpServerRequest object) {
        // todo body.getString("type") is password
        // return new UsernamePasswordCredentials(object)
        return null;
    }
}
