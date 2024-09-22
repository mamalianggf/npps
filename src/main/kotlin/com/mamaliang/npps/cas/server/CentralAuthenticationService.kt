package com.mamaliang.npps.cas.server

import com.mamaliang.npps.cas.server.authentication.AuthenticationResult
import com.mamaliang.npps.cas.server.ticket.TicketGrantingTicket

/**
 * @author gaof
 * @date 2024/9/5
 */
class CentralAuthenticationService {
    suspend fun createTicketGrantingTicket(authenticationResult: AuthenticationResult): TicketGrantingTicket? {
        val authentication = authenticationResult.authentication
        val service = authenticationResult.service
        return null
    }
}
