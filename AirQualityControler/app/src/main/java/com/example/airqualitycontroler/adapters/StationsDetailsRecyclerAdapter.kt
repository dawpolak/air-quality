package com.example.airqualitycontroler.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.airqualitycontroler.R
import com.example.airqualitycontroler.models.SensorsInStation
import com.example.airqualitycontroler.models.Station
import kotlinx.android.synthetic.main.single_row_station_details.view.*

class StationsDetailsRecyclerAdapter(val onItemClick: (Station) -> Unit) : RecyclerView.Adapter<StationsDetailsRecyclerAdapter.ViewHolder>() {
    private var stationList: MutableList<Station> = mutableListOf()
    private var sensorsList: MutableList<SensorsInStation> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_row_station_details, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return sensorsList.size
    }

    override fun onBindViewHolder(holder: StationsDetailsRecyclerAdapter.ViewHolder, position: Int) {
        holder.expandableLayout.isVisible = false
        Log.d("infoo","sensors:"+sensorsList.size.toString()+"stations:"+stationList.size.toString())
        if(!sensorsList.isEmpty()&&!stationList.isEmpty()) {

            var station = stationList[position]
            holder.stationNameText.text = station.stationName

            var sensor: SensorsInStation
            val sb = StringBuilder()
            sensor = sensorsList[position]
            for (item: Int in sensor.listOfSensors.indices) {
                sb.appendln(sensor.listOfSensors[item].param.paramFormula + ": " + sensor.listOfValues[item].values[0].value)
            }
            holder.sensors.text = sb
        }
    }

    fun setStations(stations: List<Station>) {
        this.stationList = stations as MutableList<Station>
        //notifyDataSetChanged()
    }

    fun setSensors(sensors: List<SensorsInStation>) {
        this.sensorsList = sensors as MutableList<SensorsInStation>
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val stationNameText: TextView = view.stationName
        val expandableLayout: ConstraintLayout=view.expanableLayout
        val sensors: TextView = view.textView

        init {
            stationNameText.setOnClickListener()
           {
                if (expandableLayout.isVisible) expandableLayout.isVisible = false else expandableLayout.isVisible = true
           }
        }
    }



}