package com.test.locksettingsconfiguration.api

import retrofit2.http.GET

interface ParameterService {
    @GET("v3/d5f5d613-474b-49c4-a7b0-7730e8f8f486/")
    suspend fun getLockParameters(): Map<String?, Any?>?

}