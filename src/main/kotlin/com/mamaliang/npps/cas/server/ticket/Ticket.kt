package com.mamaliang.npps.cas.server.ticket

import java.time.ZonedDateTime

/**
 * ticket通用概念接口
 */
interface Ticket {
    /**
     * ticket的id
     */
    val id: String

    /**
     * ticket的前缀
     */
    val prefix: String

    /**
     * ticket的使用次数
     */
    val countOfUses: Int

    /**
     * 是否过期，大多数的实现和ExpirationPolicy相关联
     * @see ExpirationPolicy
     */
    var isExpired: Boolean

    /**
     * ticket的过期策略
     */
    val expirationPolicy: ExpirationPolicy

    /**
     * ticket的创建时间
     */
    val creationTime: ZonedDateTime

    /**
     * 获取ticket的最后使用时间
     *
     * @return ticket的最后使用时间
     */
    var lastTimeUsed: ZonedDateTime

    /**
     * ticket的倒数第二次使用时间
     */
    val previousTimeUsed: ZonedDateTime

    /**
     * 通常情况下需更新一下内容
     * 1. 倒数第二次使用时间
     * 2. 最后使用时间
     * 3. 使用次数
     */
    fun update()
}
