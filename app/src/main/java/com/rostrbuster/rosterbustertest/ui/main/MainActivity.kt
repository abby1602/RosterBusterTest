package com.rostrbuster.rosterbustertest.ui.main

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rostrbuster.rosterbustertest.BR
import com.rostrbuster.rosterbustertest.R
import com.rostrbuster.rosterbustertest.ui.flightDetail.FlightDetailActivity

class MainActivity : AppCompatActivity() {

    var mainViewModel = MainViewModel()
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)


        //Data binding
        val binding = DataBindingUtil.setContentView<ViewDataBinding>(this,
            R.layout.activity_main
        )

        //set up viewmodel
        mainViewModel =  ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, mainViewModel)


        setObservers()

    }

    fun showDialog(mContext: Activity) {
        if (progressDialog == null && !mContext.isFinishing) {
            progressDialog = ProgressDialog(mContext)
            progressDialog?.show()
        }
    }

    fun dismissDialog(mContext: Activity) {
        if (progressDialog != null && progressDialog!!.isShowing && !mContext.isFinishing)
            progressDialog?.dismiss()
        progressDialog = null
    }

    /**
     * setup of observers
     * */
    fun setObservers(){

        mainViewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading) {
                showDialog(this)
            } else {
                dismissDialog(this)
            }
        })

        mainViewModel.showToast.observe(this, Observer { showToast ->
            if (showToast) {

                Toast.makeText(this,mainViewModel.showToastValue, Toast.LENGTH_LONG).show()
            }
        })


        mainViewModel.flightItemClick.observe(this, Observer{

            if (it){

                var intent = Intent(this, FlightDetailActivity::class.java)
                intent.putExtra("model", mainViewModel.flightStatusResponseItem)
                startActivity(intent)
            }
        })

    }
}