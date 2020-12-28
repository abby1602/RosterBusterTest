package com.rostrbuster.rosterbustertest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rostrbuster.rosterbustertest.ui.main.MainViewModel
import com.rostrbuster.rosterbustertest.R
import com.rostrbuster.rosterbustertest.databinding.RowFlightItemBinding
import com.rostrbuster.rosterbustertest.utils.loadImage

class FlightStatusAdapter(var viewModel : MainViewModel) : RecyclerView.Adapter<FlightStatusAdapter.ViewHolder>() {

    class ViewHolder(val binding : RowFlightItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =

        ViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_flight_item, parent, false)
        )

    override fun getItemCount(): Int = viewModel.flightstatusList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var model = viewModel.flightstatusList[position]

        holder.binding.viewModel = viewModel
        holder.binding.model = model

        when(model.DutyID) {

            "FLT" -> {

                loadImage(holder.binding.imgFlight, R.drawable.ic_plane)

                holder.binding.txtDepatDesti.text = model.Departure + " - " + model.Destination
                holder.binding.txtTime.text = model.Time_Depart + " - " + model.Time_Arrive
                holder.binding.txtStatus.text = ""

            }

            "OFD" -> {

                //Layover
                loadImage(holder.binding.imgFlight, R.drawable.ic_briefcase)

                holder.binding.txtDepatDesti.text = "Layover"
                holder.binding.txtTime.text = model.Time_Depart + " - " + model.Time_Arrive
                holder.binding.txtStatus.text = model.Destination
            }

            "DO" -> {

                //OFF
                loadImage(holder.binding.imgFlight, R.drawable.ic_standby)

                holder.binding.txtDepatDesti.text = "OFF"
                holder.binding.txtTime.text = "OFF"
                holder.binding.txtStatus.text = ""
            }

            "POS" -> {
                //POSITIONING
                loadImage(holder.binding.imgFlight, R.drawable.ic_standby)

                holder.binding.txtDepatDesti.text = model.Departure + " - " + model.Destination
                holder.binding.txtTime.text = model.Time_Depart + " - " + model.Time_Arrive
                holder.binding.txtStatus.text = "POSITIONING"

            }

            "SBY" -> {

                //Standby
                loadImage(holder.binding.imgFlight, R.drawable.ic_standby)

                holder.binding.txtDepatDesti.text = "Standby"
                holder.binding.txtTime.text = model.Time_Arrive + " - " + model.Time_Arrive
                holder.binding.txtStatus.text = "SBY" + "("+model.Destination+")"

            }
            else ->{

                loadImage(holder.binding.imgFlight, R.drawable.ic_plane)

                holder.binding.txtDepatDesti.text = model.Departure + " - " + model.Destination
                holder.binding.txtTime.text = model.Time_Depart + " - " + model.Time_Arrive
                holder.binding.txtStatus.text = ""

            }


        }

    }
}