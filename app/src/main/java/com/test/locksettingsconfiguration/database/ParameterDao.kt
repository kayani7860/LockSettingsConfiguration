package com.test.locksettingsconfiguration.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update import kotlinx.coroutines.flow.Flow
import java.lang.reflect.Parameter

@Dao
interface ParameterDao {
    @Insert
    suspend fun addParams(parameter: Parameter)

/*    @Query("SELECT * FROM parameter")
    suspend fun getParams(parameter: Parameter1) : Parameter1*/
}