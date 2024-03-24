package com.test.locksettingsconfiguration.database

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.locksettingsconfiguration.model.ParameterModel

class MySharedPrefs(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveList(key: String, list: List<ParameterModel>) {
        val json = gson.toJson(list)
        println("saving list $list")
        sharedPreferences.edit().putString(key, json).apply()
    }

    fun getList(key: String): List<ParameterModel>? {
        val json = sharedPreferences.getString(key, null)
        return gson.fromJson(json, object : TypeToken<List<ParameterModel>>() {}.type)
    }
}

