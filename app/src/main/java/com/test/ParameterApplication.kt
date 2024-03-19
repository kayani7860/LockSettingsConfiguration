package com.test

import android.app.Application
import com.test.locksettingsconfiguration.api.ParameterService
import com.test.locksettingsconfiguration.api.RetrofitHelper
import com.test.locksettingsconfiguration.database.ParameterDatabase
import com.test.locksettingsconfiguration.repository.ParameterRepository

class ParameterApplication : Application() {

    lateinit var parameterRepository: ParameterRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val parameterService = RetrofitHelper.getInstance().create(ParameterService::class.java)
       // val database = ParameterDatabase.getDataBase(applicationContext)
        parameterRepository = ParameterRepository(parameterService)
    }
}