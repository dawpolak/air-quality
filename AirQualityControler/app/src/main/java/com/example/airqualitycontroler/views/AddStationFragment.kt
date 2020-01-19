package com.example.airqualitycontroler.views

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.airqualitycontroler.R
import com.example.airqualitycontroler.adapters.StationsRecyclerAdapter
import com.example.airqualitycontroler.models.FavouriteId
import com.example.airqualitycontroler.models.Station
import com.example.airqualitycontroler.viewmodels.AddStationViewModel
import com.example.airqualitycontroler.viewmodels.StationsViewModel



class AddStationFragment : Fragment() {

    companion object {
        fun newInstance() = AddStationFragment()
    }
    lateinit var myAdapter: StationsRecyclerAdapter
    lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: AddStationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_station_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView = getView()!!.findViewById(R.id.add_station_recycler)

        val autotextView = getView()!!.findViewById<AutoCompleteTextView>(R.id.cityAutoCompleteTextView)

        viewModel = ViewModelProviders.of(this).get(AddStationViewModel::class.java)

        var stations = listOf<Station>()
        viewModel.listOfStations.observe(viewLifecycleOwner, Observer<List<Station>> {
                t -> stations=t
            var stationsName:List<String> = stations.map { it.city.name }

            autotextView.setAdapter(ArrayAdapter<String>(activity!!, android.R.layout.simple_list_item_1, stationsName))
        })

        val button = getView()!!.findViewById<Button>(R.id.searchButton)
        if (button != null)
        {
            button ?.setOnClickListener(View.OnClickListener {
                viewModel.listOfStations.observe(viewLifecycleOwner, Observer<List<Station>> { t ->
                    myAdapter.setStationsInCity(t!!,autotextView.getText().toString())
                })
                val enteredText = autotextView.getText()
                Toast.makeText(activity, enteredText, Toast.LENGTH_SHORT).show()
            })

        }
        recyclerLoad()

    }
    private fun recyclerLoad() {

        myAdapter = StationsRecyclerAdapter {arg1,arg2 ->

            Log.d("item", "Klikasz w: ${arg1} ${arg2}")
            //viewModel.insert(arg1 as FavouriteId)

        }

        recyclerView.apply{
            layoutManager = LinearLayoutManager(activity!!, RecyclerView.VERTICAL,false)
            hasFixedSize()
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            itemAnimator = DefaultItemAnimator()
            adapter = myAdapter
        }
    }

}
