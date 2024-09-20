//package com.mamaliang.npps.cas.server.service;
//
//
//import com.mamaliang.npps.cas.server.authentication.principal.Service;
//
//import java.util.Collection;
//import java.util.function.Predicate;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
///**
// * service manager interface
// * 用于存储、查询、匹配 service
// */
//public interface ServicesManager {
//
//  /**
//   * 添加service或更新已存在的service
//   *
//   * @param registeredService 待添加或更新的service
//   * @return 新service
//   */
//  RegisteredService save(RegisteredService registeredService);
//
//  /**
//   * 删除service
//   *
//   * @param id 待删除service的id
//   * @return 被删除的service，若不存在则返回null
//   */
//  RegisteredService delete(long id);
//
//  /**
//   * @see #delete(long)
//   */
//  RegisteredService delete(RegisteredService svc);
//
//  /**
//   * 查找service
//   *
//   * @param serviceId service id
//   * @return 查找到的service
//   */
//  RegisteredService findServiceBy(String serviceId);
//
//  /**
//   * @see #findServiceBy(long)
//   */
//  RegisteredService findServiceBy(Service service);
//
//  /**
//   * Find a collection of services by type.
//   *
//   * @param clazz the clazz
//   * @return the collection of services that matches the supplied type
//   */
//  Collection<RegisteredService> findServiceBy(Predicate<RegisteredService> clazz);
//
//  /**
//   * Find service by type.
//   *
//   * @param <T>       the type parameter
//   * @param serviceId the service id
//   * @param clazz     the clazz
//   * @return the t
//   */
//  <T extends RegisteredService> T findServiceBy(Service serviceId, Class<T> clazz);
//
//  /**
//   * Find service by type.
//   *
//   * @param <T>       the type parameter
//   * @param serviceId the service id
//   * @param clazz     the clazz
//   * @return the t
//   */
//  <T extends RegisteredService> T findServiceBy(String serviceId, Class<T> clazz);
//
//  /**
//   * Find a RegisteredService by matching with the supplied id.
//   *
//   * @param id the id to match with.
//   * @return the RegisteredService that matches the supplied service.
//   */
//  RegisteredService findServiceBy(long id);
//
//  /**
//   * Retrieve the collection of all registered services.
//   * Services that are returned are valid, non-expired, etc.
//   *
//   * @return the collection of all services.
//   */
//  Collection<RegisteredService> getAllServices();
//
//  /**
//   * Convenience method to let one know if a service exists in the data store.
//   *
//   * @param service the service to check.
//   * @return true if it exists, false otherwise.
//   */
//  boolean matchesExistingService(Service service);
//
//  /**
//   * Convenience method to let one know if a service exists in the data store.
//   *
//   * @param service the service to check.
//   * @return true if it exists, false otherwise.
//   */
//  boolean matchesExistingService(String service);
//
//  /**
//   * Inform the ServicesManager to reload its list of services if its cached
//   * them. Note that this is a suggestion and that ServicesManagers are free
//   * to reload whenever they want.
//   */
//  void load();
//
//  /**
//   * 加载的service数量
//   *
//   * @return 加载的service数量
//   */
//  int count();
//
//}
