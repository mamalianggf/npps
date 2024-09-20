//package com.mamaliang.npps.cas.server.ticket;
//
//
//import com.mamaliang.npps.cas.server.authentication.Authentication;
//import com.mamaliang.npps.cas.server.authentication.principal.Service;
//
//import java.util.Map;
//
///**
// * TicketGrantingTicket接口
// */
//public interface TicketGrantingTicket extends Ticket {
//
//  String PREFIX = "TGT";
//
//  /**
//   * 获取authentication
//   *
//   * @return authentication
//   */
//  Authentication getAuthentication();
//
//  /**
//   * 给特定的service发放ServiceTicket
//   *
//   * @param id               ticket的唯一标识符
//   * @param service          service
//   * @param expirationPolicy 过期策略
//   * @return ServiceTicket
//   */
//  ServiceTicket grantServiceTicket(String id, Service service, ExpirationPolicy expirationPolicy);
//
//  /**
//   * TicketGrantingTicket授予过的service ticket id和service
//   * key: service ticket id
//   * value: Service
//   *
//   * @return TicketGrantingTicket授予过的service ticket id和service
//   */
//  Map<String, Service> getServices();
//
//  /**
//   * 移除所有的services（退出时）
//   */
//  void removeAllServices();
//
//  /**
//   * 标记为过期
//   */
//  void markTicketExpired();
//}
