package com.test.locksettingsconfiguration.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.locksettingsconfiguration.api.ParameterService
import com.test.locksettingsconfiguration.isNetworkAvailable
import com.test.locksettingsconfiguration.model.LockConfig
import com.test.locksettingsconfiguration.model.Parameter
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.json.Json

class ParameterRepository : ParameterService {

    private val url = "https://run.mocky.io/v3/d5f5d613-474b-49c4-a7b0-7730e8f8f486"
    private val parameterLiveData = MutableLiveData<List<Parameter>>()
    val parameter: LiveData<List<Parameter>>
        get() = parameterLiveData

    override suspend fun getLockParameters() {
        if (isNetworkAvailable) {
            try {
                val client = HttpClient {
                    install(ContentNegotiation) {
                        json(Json {
                            ignoreUnknownKeys = true
                        })
                    }
                }

                val response = client
                    .get(url).body<LockConfig>()

                val dataList = listOf(
                    Parameter(
                        "Lock Voltage",
                        response.lockVoltage.default,
                        response.lockVoltage.default,
                        response.lockVoltage
                    ),
                    Parameter(
                        "Lock Kick",
                        response.lockKick.default,
                        response.lockKick.default,
                        response.lockKick
                    ),
                    Parameter(
                        "Lock Type",
                        response.lockType.default,
                        response.lockType.default,
                        response.lockType
                    ),
                    Parameter(
                        "Lock Release",
                        response.lockRelease.default,
                        response.lockRelease.default,
                        response.lockRelease
                    )
                )
                parameterLiveData.postValue(dataList)

            } catch (error: Exception) {
            throw IOException(error.message ?: "Unknown Error")
        }
    }
    }

}