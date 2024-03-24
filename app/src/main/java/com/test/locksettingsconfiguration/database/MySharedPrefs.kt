package com.test.locksettingsconfiguration.database

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.locksettingsconfiguration.model.Parameter

/*class MySharedPrefs(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveList(key: String, list: List<Parameter>) {
        val json = gson.toJson(list)
        println("saving list $list")
        sharedPreferences.edit().putString(key, json).apply()
    }

    fun getList(key: String): List<Parameter>? {
        val json = sharedPreferences.getString(key, null)
        return gson.fromJson(json, object : TypeToken<List<Parameter>>() {}.type)
    }
}*/

