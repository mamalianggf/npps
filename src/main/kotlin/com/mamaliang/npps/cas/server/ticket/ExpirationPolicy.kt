package com.mamaliang.npps.cas.server.ticket

/**
 * ticket过期策略接口
 */
interface ExpirationPolicy {
    /**
     * ticket允许的最大存活时间,单位秒
     */
    val timeToLive: Long

    /**
     * ticket允许的最大空闲时间
     *
     * @return 最大空闲时间，单位秒
     */
    val timeToIdle: Long

    /**
     * 过期策略名称
     */
    val name: String
}
