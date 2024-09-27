package com.mamaliang.npps.cas.server.authentication.handler.impl

import com.mamaliang.npps.cas.server.authentication.credential.Credential
import com.mamaliang.npps.cas.server.authentication.credential.impl.UsernamePasswordCredential
import com.mamaliang.npps.cas.server.authentication.handler.AuthenticationHandler
import com.mamaliang.npps.cas.server.authentication.handler.AuthenticationHandlerResult
import com.mamaliang.npps.cas.server.authentication.principal.SimplePrincipal
import com.mamaliang.npps.common.RmsResponse
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
        val userPass = credential as UsernamePasswordCredential
        val requestBody = JsonObject()
        requestBody.put("loginName", userPass.username)
        requestBody.put("password", userPass.password)
        val response = HttpClient.getRmsClient()
            .post("http://127.0.0.1:60700/users/login?withStatus=true")
            .sendJsonObject(requestBody)
            .coAwait()

        val responseBody = response.bodyAsJson(RmsResponse::class.java)
        when (responseBody) {
            is com.mamaliang.npps.common.RmsErrorResponse -> TODO()
            is com.mamaliang.npps.common.RmsSuccessResponse -> {
                responseBody.data
            }
        }
        val attributes = response.bodyAsJsonObject().get<MutableMap<String, Any>>("data")
        val principal = SimplePrincipal(credential.username, attributes)
        return AuthenticationHandlerResult(credential, principal)
    }

    override fun supports(credential: Credential): Boolean {
        return credential is UsernamePasswordCredential
    }

}