package com.test.locksettingsconfiguration.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.locksettingsconfiguration.api.ParameterService
import com.test.locksettingsconfiguration.isNetworkAvailable
import com.test.locksettingsconfiguration.model.LockConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.json.Json

class ParameterRepository : ParameterService {

    val API_URL = "https://run.mocky.io/v3/d5f5d613-474b-49c4-a7b0-7730e8f8f486"
    private val parameterLiveData = MutableLiveData<LockConfig?>()
    val parameter: LiveData<LockConfig?>
        get() = parameterLiveData

    override suspend fun getLockParameters(): LockConfig? {
        if (!isNetworkAvailable) return null

        val client = HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
        return try {
            val response = client
                .get(API_URL)
            parameterLiveData.postValue(response.body<LockConfig>())
            response.body<LockConfig?>()

        } catch (error: Exception) {
            println("hammad ERROR ${error.message}")
            throw IOException(error.message ?: "hammad Unknown Error")
        }
    }

}