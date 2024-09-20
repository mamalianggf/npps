package com.mamaliang.npps.cas.server.service;

import io.vertx.redis.client.RedisAPI;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * ServiceRegistryDao的redis实现
 */
@Slf4j
public class RedisServiceRegistryDao implements ServiceRegistryDao {

  private static final String CAS_SERVICE_PREFIX = "SERVICE:";

  private RedisAPI redisAPI;

  public RedisServiceRegistryDao(RedisAPI redisAPI) {
    this.redisAPI = redisAPI;
  }

  @Override
  public RegisteredService save(RegisteredService registeredService) {
    try {
      final String redisKey = getRegisteredServiceRedisKey(registeredService.getId());
      redisAPI.set
        this.template.boundValueOps(redisKey).set(rs);
    } catch (final Exception e) {
      LOGGER.error(e.getMessage(), e);
    }
    return rs;
  }

  @Override
  public boolean delete(RegisteredService registeredService) {
    return false;
  }

  @Override
  public List<RegisteredService> load() {
    return List.of();
  }

  @Override
  public RegisteredService findServiceById(long id) {
    return null;
  }

  @Override
  public String getName() {
    return "";
  }


  private static String getRegisteredServiceRedisKey(final long id) {
    return CAS_SERVICE_PREFIX + id;
  }
}
