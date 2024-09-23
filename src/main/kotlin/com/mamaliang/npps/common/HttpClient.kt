package com.mamaliang.npps.common

import io.vertx.core.Vertx
import io.vertx.ext.web.client.WebClient
import io.vertx.ext.web.client.WebClientOptions

/**
 * @author gaof
 * @date 2024/9/23
 */
object HttpClient {

    private var rmsClient: WebClient? = null

    fun initialize(vertx: Vertx) {
        if (rmsClient == null) {
            val options = WebClientOptions()
                .setUserAgent("NPPS")
            rmsClient = WebClient.create(vertx, options);
        }
    }

    fun getRmsClient(): WebClient {
        checkNotNull(rmsClient) { "HttpClient is not initialized. Call initialize() first." }
        return rmsClient!!
    }
}