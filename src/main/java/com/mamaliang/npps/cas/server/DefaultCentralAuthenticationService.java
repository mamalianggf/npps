//package com.mamaliang.npps.cas.server;
//
//import com.mamaliang.npps.cas.server.authentication.AuthenticationResult;
//import com.mamaliang.npps.cas.server.ticket.TicketGrantingTicket;
//
///**
// * @author gaof
// * @date 2024/9/5
// */
//public class DefaultCentralAuthenticationService implements CentralAuthenticationService {
//  @Override
//  public TicketGrantingTicket createTicketGrantingTicket(AuthenticationResult authenticationResult) {
//
//    final Authentication authentication = authenticationResult.getAuthentication();
//    final Service service = authenticationResult.getService();
//    if (service != null) {
//      final RegisteredService registeredService = this.servicesManager.findServiceBy(selectedService);
//      RegisteredServiceAccessStrategyUtils.ensurePrincipalAccessIsAllowedForService(selectedService, registeredService,
//        authentication, false);
//    }
//    return null;
//  }
//}
