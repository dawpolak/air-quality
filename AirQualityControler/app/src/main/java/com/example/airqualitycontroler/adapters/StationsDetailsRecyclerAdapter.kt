package com.example.airqualitycontroler.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.airqualitycontroler.R
import com.example.airqualitycontroler.models.SensorsInStation
import com.example.airqualitycontroler.models.StationWithSensors
import kotlinx.android.synthetic.main.single_row_station_details.view.*

class StationsDetailsRecyclerAdapter : RecyclerView.Adapter<StationsDetailsRecyclerAdapter.ViewHolder>() {
    private var stationsList: MutableList<StationWithSensors> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_row_station_details, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return stationsList.size
    }

    override fun onBindViewHolder(holder: StationsDetailsRecyclerAdapter.ViewHolder, position: Int) {
        holder.expandableLayout.isVisible = false
        if(!stationsList.isEmpty()){

            holder.stationNameText.text = stationsList[position].station.stationName

            var sensors: SensorsInStation
            val sb = StringBuilder()
            sensors = stationsList[position].sensorsInStation


            if(sensors.listOfSensors.size!=0) {

                for (item: Int in sensors.listOfSensors.indices) {

                    if(sensors.listOfValues[item].values.size!=0)
                    {
                        for(i in 0..sensors.listOfValues[item].values.size)
                        {
                            if(sensors.listOfValues[item].values[i].value!=0.0)
                            {
                                sb.appendln(sensors.listOfSensors[item].param.paramFormula + ": " + sensors.listOfValues[item].values[i].value)
                                break
                            }
                        }
                    }else
                    {
                        sb.appendln(sensors.listOfSensors[item].param.paramFormula + ": brak danych")
                    }
                }
            }else sb.append("brak sensorów")
            holder.sensors.text = sb
        }
    }

    fun setStationsWithSensors(ststionsWithSensors: List<StationWithSensors>) {
        this.stationsList = ststionsWithSensors as MutableList<StationWithSensors>
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