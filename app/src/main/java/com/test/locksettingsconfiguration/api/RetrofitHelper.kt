package com.test.locksettingsconfiguration.api

import com.test.locksettingsconfiguration.model.LockParameters
import com.test.locksettingsconfiguration.model.Range
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetrofitClient {

    private const val BASE_URL = "https://run.mocky.io/"

   fun create(): ParameterService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ParameterService::class.java)
    }
}
suspend fun fetchLockParameters(): Map<String, LockParameters> {
    val apiService = RetrofitClient.create()

    val lockParametersMap = apiService.getLockParameters()
    val result = mutableMapOf<String, LockParameters>()

    lockParametersMap?.forEach { (key, value) ->
        val lockParameter = when (value) {
            is Map<*, *> -> {
                val values = (value["values"] as? List<*>)?.mapNotNull { it as? String }
                val range = (value["range"] as? Map<*, *>)?.let { rangeMap ->
                    Range(
                        min = rangeMap["min"] as? Double?,
                        max = rangeMap["max"] as? Double?
                    )
                }
                LockParameters(
                    values = values,
                    default = value["default"] as? String?,
                    range = range,
                    unit = value["unit"] as? String?,
                    common = value["common"] as? Boolean?
                )
            }
            else -> {
                LockParameters(default = value.toString())
            }
        }
        if (key!= null)
        result[key] = lockParameter
    }
    return result
}

