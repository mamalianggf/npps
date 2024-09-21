package com.mamaliang.npps.cas.server.service;

import com.mamaliang.npps.cas.server.authentication.principal.Service;

/**
 * registered service
 */
public interface RegisteredService {

  /**
   * service的唯一标识
   *
   * @return service的唯一标识
   */
  long getId();

  /**
   * service name
   *
   * @return service name
   */
  String getName();

  /**
   * service description
   *
   * @return service description
   */
  String getDescription();

  /**
   * {@link #getId()}
   */
  void setId(long id);

  /**
   * Returns whether the service matches the registered service.
   * <p>Note, as of 3.1.2, matches are case insensitive.
   *
   * @param service the service to match.
   * @return true if they match, false otherwise.
   */
  boolean matches(Service service);
}
