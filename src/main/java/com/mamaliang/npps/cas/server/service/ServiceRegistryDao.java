package com.mamaliang.npps.cas.server.service;

import java.util.List;

/**
 * service注册中心的dao层接口
 */
public interface ServiceRegistryDao {

  /**
   * 持久化service（可用于更新）
   *
   * @param registeredService 待持久化的service
   * @return 更新后的service
   */
  RegisteredService save(RegisteredService registeredService);

  /**
   * 移除service
   *
   * @param registeredService 待移除的service
   * @return 如果成功移除发回true，否则返回false
   */
  boolean delete(RegisteredService registeredService);

  /**
   * 检索所有的service
   *
   * @return services集合
   */
  List<RegisteredService> load();

  /**
   * 根据id查找service
   *
   * @param id service id
   * @return service
   */
  RegisteredService findServiceById(long id);

  /**
   * 返回该注册中心的友好名称
   *
   * @return 友好名称
   */
  String getName();
}
