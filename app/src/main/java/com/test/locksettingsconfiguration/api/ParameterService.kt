package com.test.locksettingsconfiguration.api

import com.test.locksettingsconfiguration.model.LockConfig

interface ParameterService {
    suspend fun getLockParameters(): LockConfig?
}