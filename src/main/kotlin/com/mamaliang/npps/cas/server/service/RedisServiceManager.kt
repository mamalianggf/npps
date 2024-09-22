package com.mamaliang.npps.cas.server.service

import io.vertx.core.json.Json
import io.vertx.kotlin.coroutines.coAwait
import io.vertx.redis.client.Redis
import io.vertx.redis.client.RedisAPI

/**
 * @author gaof
 * @date 2024/9/20
 */
class RedisServiceManager(redis: Redis) : ServiceManager {

  val CAS_SERVICE_PREFIX: String = "SERVICE:"
  var redisAPI: RedisAPI = RedisAPI.api(redis)

  override suspend fun save(registeredService: RegisteredService) {
    var key = getRegisteredServiceRedisKey(registeredService.id)
    var value = Json.encode(registeredService)
    // todo 是否需要处理response
    redisAPI.set(listOf(key, value)).coAwait()
  }

  override suspend fun delete(registeredService: RegisteredService): Boolean {
    var key = getRegisteredServiceRedisKey(registeredService.id)
    var responses = redisAPI.del(listOf(key)).coAwait()
    return responses.toBoolean()
  }

  override suspend fun findServiceById(id: Long): RegisteredService {
    var key = getRegisteredServiceRedisKey(id)
    var responses = redisAPI.get(key).coAwait()
    return Json.decodeValue(responses.toString(), RegisteredService::class.java)
  }

  override suspend fun load(): List<RegisteredService> {
    TODO("Not yet implemented")
  }

  fun getRegisteredServiceRedisKey(id: Long): String {
    return CAS_SERVICE_PREFIX + id
  }
}
