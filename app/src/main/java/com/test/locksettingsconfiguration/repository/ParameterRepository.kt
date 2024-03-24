package com.test.locksettingsconfiguration.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.locksettingsconfiguration.api.ParameterService
import com.test.locksettingsconfiguration.isNetworkAvailable
import com.test.locksettingsconfiguration.model.LockConfig
import com.test.locksettingsconfiguration.model.ParameterModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.json.Json

class ParameterRepository : ParameterService {

    val API_URL = "https://run.mocky.io/v3/d5f5d613-474b-49c4-a7b0-7730e8f8f486"
    private val parameterLiveData = MutableLiveData<List<ParameterModel>>()
    val parameter: LiveData<List<ParameterModel>>
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
                    .get(API_URL).body<LockConfig>()

                val dataList = listOf(
                    ParameterModel("Lock Voltage", response.lockVoltage),
                    ParameterModel("Lock Kick", response.lockKick),
                    ParameterModel("Lock Type", response.lockType),
                    ParameterModel("Lock Release", response.lockRelease)
                )
                parameterLiveData.postValue(dataList)

            } catch (error: Exception) {
            throw IOException(error.message ?: "Unknown Error")
        }
    }
    }

}