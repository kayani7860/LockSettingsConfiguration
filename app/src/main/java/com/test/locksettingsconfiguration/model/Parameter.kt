package com.test.locksettingsconfiguration.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class LockParameters(
    val name:String? = null,
    val values: List<String>? = null,
    val default: String? = null,
    val range: Range? = null,
    val unit: String? = null,
    val common: Boolean? = null
)

//@Entity(tableName = "parameter")
data class Parameter1(
    @PrimaryKey(autoGenerate = true)
    val parameterId :Int,
    val name:String? = null,
    val values:String? = null,
    val default: String,
    val range: Range,
    val unit: String,
    val common: Boolean = false
)

data class Range(
    val min: Double? = null,
    val max: Double? = null
)

