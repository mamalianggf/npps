package com.mamaliang.npps.cas.server.authentication.credential.impl

import com.mamaliang.npps.cas.server.authentication.credential.Credential

/**
 * @author gaof
 * @date 2024/9/23
 */
class UsernamePasswordCredential(
    val username: String,
    val password: String
) : Credential {
    override val id: String
        get() = username
}