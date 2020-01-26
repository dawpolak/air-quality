package com.example.airqualitycontroler.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.airqualitycontroler.R
import com.example.airqualitycontroler.models.Station
import android.widget.CheckBox
import android.widget.Toast
import com.example.airqualitycontroler.models.FavouriteId
import kotlinx.android.synthetic.main.single_row_station.view.*
import kotlinx.android.synthetic.main.single_row_station_details.view.stationName


class StationsRecyclerAdapter(val onItemClick: (Station,Boolean) -> Unit) : RecyclerView.Adapter<StationsRecyclerAdapter.ViewHolder>() {
    private var stationList: MutableList<Station> = mutableListOf()
    private var favId: List<FavouriteId> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_row_station, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return stationList.size
    }

    override fun onBindViewHolder(holder: StationsRecyclerAdapter.ViewHolder, position: Int) {
        var station = stationList[position]

        holder.stationNameText.text = station.stationName
        //holder.isFavourite.tag = position
        favId.forEach{
            Log.d("dupa","forach"+it.favouriteId +" "+station.id)
            if(it.favouriteId==station.id)holder.isFavourite.isChecked = true
        }

    }

//    fun setStations(stations: List<Station>) {
//        this.stationList = stations as MutableList<Station>
//        notifyDataSetChanged()
//    }

    fun setStationsInCity(stations: List<Station>,city: String) {
        val stationsInCity = stations.filter { it.city.name.equals(city)}
        this.stationList = stationsInCity as MutableList<Station>
        notifyDataSetChanged()
    }

    fun setFavId(fav: List<FavouriteId>) {
        this.favId = fav as MutableList<FavouriteId>
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val stationNameText: TextView = view.stationName
        val isFavourite: CheckBox = view.checkBoxFavourite


        init {
            isFavourite.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                        onItemClick(stationList[adapterPosition],view.checkBoxFavourite.isChecked)
                        }
            }
        }
    }


}