package com.rostrbuster.rosterbustertest.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rostrbuster.rosterbustertest.adapter.FlightStatusAdapter
import com.rostrbuster.rosterbustertest.data.FlightStatusResponse
import com.rostrbuster.rosterbustertest.data.FlightStatusResponseItem
import com.rostrbuster.rosterbustertest.utils.ApiClient
import com.rostrbuster.rosterbustertest.utils.ApiInterface
import com.rostrbuster.rosterbustertest.utils.App
import com.rostrbuster.rosterbustertest.utils.NetworkAvailability
import com.rostrbuster.rosterbustertest.utils.room.FlightStatusEntity
import com.rostrbuster.rosterbustertest.utils.room.LocalDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel(){

    var apiClient = ApiClient.getClient().create(ApiInterface::class.java)

    var isLoading = MutableLiveData<Boolean>()
    var showToast = MutableLiveData(false)
    var showToastValue = ""

    var flightstatusList = ArrayList<FlightStatusResponseItem>()
    var flightStatusAdapter = FlightStatusAdapter(this)

    var swipeRefreshLayoutVisibility = MutableLiveData(false)

    var flightItemClick = MutableLiveData(false)

    var flightStatusResponseItem = FlightStatusResponseItem()

    init {

        loadFlightStatus()

    }

    fun onRefresh() {
        swipeRefreshLayoutVisibility.value  = true
        loadFlightStatus()
    }

    fun loadFlightStatus(){

        if (NetworkAvailability.isNetworkAvailable()){

            isLoading.value = true

            apiClient.loadFlightStatus().enqueue(object : Callback<FlightStatusResponse>{
                override fun onFailure(call: Call<FlightStatusResponse>, t: Throwable) {

                    swipeRefreshLayoutVisibility.value = false
                    isLoading.value = false
                    Log.e("fail", t.message)
                    showToast.value = true
                    showToastValue = t.message?:""
                }

                override fun onResponse(call: Call<FlightStatusResponse>, response: Response<FlightStatusResponse>) {

                    swipeRefreshLayoutVisibility.value = false
                    isLoading.value = false

                    flightstatusList.clear()

                    response.body()?.let { flightstatusList.addAll(it) }

                    flightStatusAdapter.notifyDataSetChanged()

                    val tempList= ArrayList<FlightStatusEntity>()


                    response.body()?.forEach {
                        val strobj = App.instance.gson.toJson(it).toString()


                        tempList.add(App.instance.gson.fromJson(strobj, FlightStatusEntity::class.java))
                    }

                    GlobalScope.launch(Dispatchers.IO) {
                        LocalDatabase.getDatabase().flightStatusDAO().deleteAllFlightStatusList()
                        LocalDatabase.getDatabase().flightStatusDAO().insertAllFlightStatus(tempList)
                    }

                }
            })

        }else{

            GlobalScope.launch(Dispatchers.Main) {
                val list=  LocalDatabase.getDatabase().flightStatusDAO().getAllFlightStatusList()

                list.forEach {
                    flightstatusList.add(
                        App.instance.gson.fromJson(App.instance.gson.toJson(it).toString(),
                            FlightStatusResponseItem::class.java)
                    )
                }
                flightStatusAdapter.notifyDataSetChanged()

                isLoading.value = false

            }

        }



    }

    fun flightDetail(model : FlightStatusResponseItem){

        flightStatusResponseItem = model
        flightItemClick.value = true
    }

}