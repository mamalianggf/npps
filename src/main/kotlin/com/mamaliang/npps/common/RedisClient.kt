package com.mamaliang.npps.common

import io.vertx.core.Vertx
import io.vertx.redis.client.Redis
import io.vertx.redis.client.RedisOptions

object RedisClient {
    private var casServerRedisClient: Redis? = null
    private var casClientRedisClient: Redis? = null

    fun initialize(vertx: Vertx) {
        if (casClientRedisClient == null) {
            val options = RedisOptions()
                .setConnectionString("redis://10.0.209.174:6379/0")
            casClientRedisClient = Redis.createClient(vertx, options)
        }
        if (casServerRedisClient == null) {
            val options = RedisOptions()
                .setConnectionString("redis://10.0.209.174:6379/1")
            casServerRedisClient = Redis.createClient(vertx, options)
        }
    }

    fun getCasClientRedisClient(): Redis {
        checkNotNull(casClientRedisClient) { "RedisClientSingleton is not initialized. Call initialize() first." }
        return casClientRedisClient!!
    }

    fun getCasServerRedisClient(): Redis {
        checkNotNull(casServerRedisClient) { "RedisClientSingleton is not initialized. Call initialize() first." }
        return casServerRedisClient!!
    }
}

