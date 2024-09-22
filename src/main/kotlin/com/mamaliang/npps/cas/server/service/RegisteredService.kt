package com.mamaliang.npps.cas.server.service

import com.mamaliang.npps.cas.server.authentication.Service

/**
 * registered service
 */
interface RegisteredService {
    /**
     * service的唯一标识
     */
    val id: Long

    /**
     * service name
     */
    val name: String

    /**
     * service description
     */
    val description: String

    fun matches(service: Service): Boolean
}
