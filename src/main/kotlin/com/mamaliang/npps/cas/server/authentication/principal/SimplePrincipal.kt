package com.mamaliang.npps.cas.server.authentication.principal

/**
 * @author gaof
 * @date 2024/9/23
 */
class SimplePrincipal(
    override val id: String,
    override val attributes: MutableMap<String, Any>
) : Principal