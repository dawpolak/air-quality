package com.example.airqualitycontroler.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.airqualitycontroler.R
import com.example.airqualitycontroler.models.Station
import kotlinx.android.synthetic.main.single_row_station_details.view.*

class StationsDetailsRecyclerAdapter(val onItemClick: (Station) -> Unit) : RecyclerView.Adapter<StationsDetailsRecyclerAdapter.ViewHolder>() {
    private var stationList: MutableList<Station> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_row_station_details, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return stationList.size
    }

    override fun onBindViewHolder(holder: StationsDetailsRecyclerAdapter.ViewHolder, position: Int) {
        var station = stationList[position]

        holder.stationIdText.text = station.id.toString()
        holder.stationNameText.text = station.stationName
    }

    fun setStations(stations: List<Station>) {
        this.stationList = stations as MutableList<Station>
        notifyDataSetChanged()
    }

    fun setStationsInCity(stations: List<Station>,city: String) {
        val stationsInCity = stations.filter { it.city.name.equals(city) }
        this.stationList = stationsInCity as MutableList<Station>
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val stationIdText: TextView = view.stationId
        val stationNameText: TextView = view.stationName

        init {
            view.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onItemClick(stationList[adapterPosition])
                }
            }
        }
    }

    interface OnTaskListener {
        fun onTaskClick(position: Int)

    }

}