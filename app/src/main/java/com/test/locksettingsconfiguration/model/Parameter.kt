package com.test.locksettingsconfiguration.model

data class LockParameters(
    val values: List<String>? = null,
    val default: String? = null,
    val range: Range? = null,
    val unit: String? = null,
    val common: Boolean? = null
)

data class Range(
    val min: Double? = null,
    val max: Double? = null
)

