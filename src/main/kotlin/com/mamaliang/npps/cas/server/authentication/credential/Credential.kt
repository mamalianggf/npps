package com.mamaliang.npps.cas.server.authentication.credential

/**
 * 认证凭据
 *
 * @author gaof
 * @date 2024/9/5
 */
interface Credential {
    /**
     * Credential的标识符类如 username、userId
     * 可用于记录日志、审计等
     */
    val id: String
}
