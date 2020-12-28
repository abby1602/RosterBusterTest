package com.rostrbuster.rosterbustertest.ui.flightDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.rostrbuster.rosterbustertest.BR
import com.rostrbuster.rosterbustertest.R
import com.rostrbuster.rosterbustertest.data.FlightStatusResponseItem

class FlightDetailActivity : AppCompatActivity() {

    var flightDetailViewModel = FlightDetailViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_flight_detail)

        //Data binding
        val binding = DataBindingUtil.setContentView<ViewDataBinding>(this,
        R.layout.activity_flight_detail)

        //set up viewmodel
        flightDetailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FlightDetailViewModel::class.java)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, flightDetailViewModel)


        var getintent = intent
        flightDetailViewModel.model.value = getintent.getSerializableExtra("model") as FlightStatusResponseItem

    }
}