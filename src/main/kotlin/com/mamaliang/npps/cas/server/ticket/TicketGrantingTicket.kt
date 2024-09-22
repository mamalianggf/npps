package com.mamaliang.npps.cas.server.ticket

import com.mamaliang.npps.cas.server.authentication.Authentication
import com.mamaliang.npps.cas.server.authentication.Service


/**
 * TicketGrantingTicket接口
 */
interface TicketGrantingTicket : Ticket {

    val authentication: Authentication

    /**
     * TicketGrantingTicket授予过的service ticket id和service
     * key: service ticket id
     * value: Service
     *
     * @return TicketGrantingTicket授予过的service ticket id和service
     */
    val services: MutableMap<String, Service>

    /**
     * 给特定的service发放ServiceTicket
     *
     * @param id               ticket的唯一标识符
     * @param service          service
     * @param expirationPolicy 过期策略
     * @return ServiceTicket
     */
    fun grantServiceTicket(id: String, service: Service, expirationPolicy: ExpirationPolicy): ServiceTicket

    /**
     * 移除所有的services（退出时）
     */
    fun removeAllServices()

    /**
     * 标记为过期
     */
    fun markTicketExpired()

    companion object {
        const val PREFIX: String = "TGT"
    }

}
