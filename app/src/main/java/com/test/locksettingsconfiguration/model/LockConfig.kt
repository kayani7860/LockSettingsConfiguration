package com.test.locksettingsconfiguration.model

import kotlinx.serialization.Serializable

@Serializable
data class LockConfig(
    val lockVoltage: ParameterModel,
    val lockType: ParameterModel,
    val lockKick: ParameterModel,
    val lockRelease: ParameterModel
)