package com.rostrbuster.rosterbustertest.utils.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FlightStatusDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFlightStatus(list: List<FlightStatusEntity>)

    @Query("SELECT * from flightstatus_table")
    suspend fun getAllFlightStatusList(): List<FlightStatusEntity>


    @Query("DELETE  FROM flightstatus_table")
    suspend fun deleteAllFlightStatusList()
}