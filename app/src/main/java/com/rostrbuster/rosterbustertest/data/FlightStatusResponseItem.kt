package com.rostrbuster.rosterbustertest.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FlightStatusResponseItem(

    @SerializedName("Aircraft Type")
    val AircraftType: String = "",
    val Captain: String = "",
    val Date: String = "",
    val Departure: String = "",
    val Destination: String = "",
    val DutyCode: String = "",
    val DutyID: String = "",
    @SerializedName("First Officer")
    val FirstOfficer: String = "",
    @SerializedName("Flight Attendant")
    val FlightAttendant: String = "",
    val Flightnr: String = "",
    val Tail: String = "",
    val Time_Arrive: String = "",
    val Time_Depart: String = ""
) : Serializable