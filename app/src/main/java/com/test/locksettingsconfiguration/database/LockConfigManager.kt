package com.test.locksettingsconfiguration.database

import android.content.Context
import com.test.locksettingsconfiguration.model.LockConfig

object LockConfigManager {
    private const val LOCK_CONFIG_KEY = "lock_config"

    fun saveLockConfig(context: Context, lockConfig: LockConfig) {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(LOCK_CONFIG_KEY, lockConfig.toJson())
        editor.apply()
    }

    fun getLockConfig(context: Context): LockConfig? {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val json = sharedPreferences.getString(LOCK_CONFIG_KEY, null)
        return json?.let { LockConfig.fromJson(it) }
    }
}