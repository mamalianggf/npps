package com.mamaliang.npps.cas.server.ticket

import com.mamaliang.npps.cas.server.authentication.Service


/**
 * ServiceTicket接口
 */
interface ServiceTicket : Ticket {
    /**
     * 当前ServiceTicket是授予给哪个service的
     */
    val service: Service

    /**
     * 获取`发放当前serviceTicket`的TicketGrantingTicket
     */
    val grantingTicket: TicketGrantingTicket

    /**
     * 当前ServiceTicket是否和TicketGrantingTicket同一时间创建
     */
    val isFromNewLogin: Boolean

    /**
     * 尝试验证指定service与当前serviceTicket关联的service是否匹配
     *
     * @param service incoming service
     * @return 如果匹配成功返回true
     */
    fun isValidFor(service: Service): Boolean

    companion object {
        const val PREFIX: String = "ST"
    }
}
