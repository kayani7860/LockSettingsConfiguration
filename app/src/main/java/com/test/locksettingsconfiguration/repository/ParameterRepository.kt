package com.test.locksettingsconfiguration.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.locksettingsconfiguration.api.ParameterService
import com.test.locksettingsconfiguration.model.LockParameters
import com.test.locksettingsconfiguration.model.Range

class ParameterRepository(private val parameterService: ParameterService) {
    private val parameterLiveData = MutableLiveData<LockParameters>()
    val parameter: LiveData<LockParameters>
        get() = parameterLiveData

    suspend fun fetchLockParameters() {
        val lockParametersMap = parameterService.getLockParameters()
        lockParametersMap?.forEach { (name, property) ->

            val lockParameter = when (property) {
                is Map<*, *> -> {
                    val values = (property["values"] as? List<*>)?.mapNotNull { it as? String }
                    val range = (property["range"] as? Map<*, *>)?.let { rangeMap ->
                        Range(
                            min = rangeMap["min"] as? Double?,
                            max = rangeMap["max"] as? Double?
                        )
                    }
                    println("hammad parsing $name range = ${property["range"]}")
                    LockParameters(
                        name = name,
                        values = values,
                        default = property["default"].toString(),
                        range = range,
                        unit = property["unit"] as? String?,
                        common = property["common"] as? Boolean?
                    )
                }

                else -> {
                    LockParameters(default = property.toString())
                }
            }
            if (name != null) {
                parameterLiveData.postValue(lockParameter)
            }
        }
    }
}

