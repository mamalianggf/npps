package com.mamaliang.npps.cas.server.authentication

/**
 * 各种实体的顶级接口
 */
interface Principal {
    /**
     * 唯一描述符
     */
    val id: String

    /**
     * 属性
     */
    val attributes: MutableMap<String, Any>
}
