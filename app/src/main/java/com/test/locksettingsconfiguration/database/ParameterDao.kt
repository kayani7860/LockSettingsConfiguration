package com.test.locksettingsconfiguration.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.test.locksettingsconfiguration.model.Parameter1

@Dao
interface ParameterDao {
    @Insert
    suspend fun addParams(parameter: Parameter1)

/*    @Query("SELECT * FROM parameter")
    suspend fun getParams(parameter: Parameter1) : Parameter1*/
}