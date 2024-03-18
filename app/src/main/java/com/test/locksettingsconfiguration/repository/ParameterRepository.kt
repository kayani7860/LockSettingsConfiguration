package com.test.locksettingsconfiguration.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.locksettingsconfiguration.api.ParameterService
import com.test.locksettingsconfiguration.model.LockParameters
import java.lang.reflect.Parameter


class ParameterRepository(private val parameterService: ParameterService) {
    private val parameterLiveData = MutableLiveData<Map<String, LockParameters>>()
    val parameter: LiveData<Map<String, LockParameters>>
        get() = parameterLiveData

    suspend fun getParameter() {
        parameterService.getLockParameters()
        val result = parameterService.getLockParameters()


    }
}