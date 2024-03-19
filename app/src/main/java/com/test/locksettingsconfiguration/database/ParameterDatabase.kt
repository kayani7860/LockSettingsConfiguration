package com.test.locksettingsconfiguration.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.locksettingsconfiguration.model.Parameter1

//@Database(entities = [Parameter1::class], version = 1)
abstract class ParameterDatabase : RoomDatabase() {

    abstract fun parameterDao(): ParameterDao

    companion object {
        @Volatile
        private var INSTANCE: ParameterDatabase? = null
        fun getDataBase(context: Context): ParameterDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        ParameterDatabase::class.java,
                        "parameterDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}