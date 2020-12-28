package com.rostrbuster.rosterbustertest.utils.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "flightstatus_table")
data class FlightStatusEntity(

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "AircraftType") @SerializedName("Aircraft Type") val AircraftType: String,
    @ColumnInfo(name = "Captain") val Captain: String,
    @ColumnInfo(name = "Date") val Date: String,
    @ColumnInfo(name = "Departure") val Departure: String,
    @ColumnInfo(name = "Destination") val Destination: String,
    @ColumnInfo(name = "DutyCode") val DutyCode: String,
    @ColumnInfo(name = "DutyID") val DutyID: String,
    @ColumnInfo(name = "FirstOfficer") @SerializedName("First Officer") val FirstOfficer: String,

    @ColumnInfo(name = "FlightAttendant") @SerializedName("Flight Attendant") val FlightAttendant: String,
    @ColumnInfo(name = "Flightnr") val Flightnr: String,
    @ColumnInfo(name = "Tail") val Tail: String,
    @ColumnInfo(name = "Time_Arrive") val Time_Arrive: String,
    @ColumnInfo(name = "Time_Depart") val Time_Depart: String
)