package com.rostrbuster.rosterbustertest.ui.flightDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rostrbuster.rosterbustertest.data.FlightStatusResponseItem

class FlightDetailViewModel : ViewModel() {

    var  model = MutableLiveData<FlightStatusResponseItem>()
}