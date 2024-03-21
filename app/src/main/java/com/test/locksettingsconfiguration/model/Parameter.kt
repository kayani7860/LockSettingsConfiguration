package com.test.locksettingsconfiguration.model

import kotlinx.serialization.Serializable
import com.google.gson.Gson

@Serializable
data class Range(
    val min: Double? = null,
    val max: Double? = null
)

@Serializable
data class LockConfig(
    val lockVoltage: LockVoltageConfig,
    val lockType: LockTypeConfig,
    val lockKick: LockKickConfig,
    val lockRelease: LockReleaseConfig,
    val lockReleaseTime: LockReleaseTimeConfig,
    val lockAngle: LockAngleConfig
){
    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String): LockConfig {
            return Gson().fromJson(json, LockConfig::class.java)
        }
    }
}

@Serializable
data class LockVoltageConfig(
    val values: List<String>,
    val default: String
)

@Serializable
data class LockTypeConfig(
    val values: List<String>,
    val default: String
)

@Serializable
data class LockKickConfig(
    val values: List<String>,
    val default: String
)

@Serializable
data class LockReleaseConfig(
    val values: List<String>,
    val default: String,
    val common: Boolean = false
)

@Serializable
data class LockReleaseTimeConfig(
    val range: Range,
    val unit: String,
    val default: Double
)

@Serializable
data class LockAngleConfig(
    val range: Range,
    val unit: String,
    val default: Int,
    val common: Boolean = false
)


