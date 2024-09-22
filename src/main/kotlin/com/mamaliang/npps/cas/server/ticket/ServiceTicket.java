//package com.mamaliang.npps.cas.server.ticket;
//
//
//import com.mamaliang.npps.cas.server.authentication.Service;
//
///**
// * ServiceTicket接口
// */
//public interface ServiceTicket extends Ticket {
//
//  String PREFIX = "ST";
//
//  /**
//   * 获取service，当前ServiceTicket是授予给哪个service的
//   *
//   * @return service
//   */
//  Service getService();
//
//  /**
//   * 获取`发放当前serviceTicket`的TicketGrantingTicket
//   *
//   * @return TicketGrantingTicket
//   */
//  TicketGrantingTicket getGrantingTicket();
//
//  /**
//   * 当前ServiceTicket是否和TicketGrantingTicket同一时间创建
//   *
//   * @return 如果是返回true
//   */
//  boolean isFromNewLogin();
//
//  /**
//   * 尝试验证指定service与当前serviceTicket关联的service是否匹配
//   *
//   * @param service incoming service
//   * @return 如果匹配成功返回true
//   */
//  boolean isValidFor(Service service);
//}
