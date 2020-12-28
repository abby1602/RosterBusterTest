package com.rostrbuster.rosterbustertest.utils

import com.rostrbuster.rosterbustertest.data.FlightStatusResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {


    @GET("dummy-response.json")
    fun loadFlightStatus() : Call<FlightStatusResponse>
}