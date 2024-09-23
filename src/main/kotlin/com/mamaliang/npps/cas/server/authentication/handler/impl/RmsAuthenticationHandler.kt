package com.mamaliang.npps.cas.server.authentication.handler.impl

import com.mamaliang.npps.cas.server.authentication.credential.Credential
import com.mamaliang.npps.cas.server.authentication.credential.impl.UsernamePasswordCredential
import com.mamaliang.npps.cas.server.authentication.handler.AuthenticationHandler
import com.mamaliang.npps.cas.server.authentication.handler.AuthenticationHandlerResult
import com.mamaliang.npps.cas.server.authentication.principal.SimplePrincipal
import com.mamaliang.npps.common.HttpClient
import io.vertx.core.json.JsonObject
import io.vertx.kotlin.core.json.get
import io.vertx.kotlin.coroutines.coAwait

/**
 * @author gaof
 * @date 2024/9/23
 */
class RmsAuthenticationHandler(override val order: Int) : AuthenticationHandler {

    override suspend fun authenticate(credential: Credential): AuthenticationHandlerResult {
        var userPass = credential as UsernamePasswordCredential
        var body = JsonObject()
        body.put("loginName", userPass.username);
        body.put("password", userPass.password);
        var response = HttpClient.getRmsClient()
            .post("http://127.0.0.1:60700/users/login?withStatus=true")
            .sendJsonObject(body)
            .coAwait()
        var attributes = response.bodyAsJsonObject().get<MutableMap<String, Any>>("data")
        var principal = SimplePrincipal(credential.username, attributes)
        return AuthenticationHandlerResult(credential, principal)
    }

    override fun supports(credential: Credential): Boolean {
        return credential is UsernamePasswordCredential
    }

}