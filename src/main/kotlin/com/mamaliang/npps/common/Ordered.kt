package com.mamaliang.npps.common

interface Ordered {
    /**
     * 值越小，优先级越高
     */
    val order: Int

    companion object {
        val HIGHEST_PRECEDENCE: Int = Int.Companion.MIN_VALUE

        val LOWEST_PRECEDENCE: Int = Int.Companion.MAX_VALUE
    }
}
