package com.mamaliang.npps.cas.server.service

/**
 * @author gaof
 * @date 2024/9/20
 */
interface ServiceManager {

    suspend fun save(registeredService: RegisteredService)

    suspend fun delete(registeredService: RegisteredService): Boolean

    suspend fun findServiceById(id: Long): RegisteredService

    suspend fun load(): List<RegisteredService>
}
