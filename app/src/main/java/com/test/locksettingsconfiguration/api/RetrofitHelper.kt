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
suspend fun fetchLockParameters(): MutableList<LockParameters> {
    val apiService = RetrofitClient.create()

    val lockParametersMap = apiService.getLockParameters()
    val result = mutableListOf<LockParameters>()

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
        if (name!= null){
            result.add(lockParameter)
        }

    }
    return result
}

