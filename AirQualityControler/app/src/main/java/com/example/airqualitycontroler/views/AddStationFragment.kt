package com.example.airqualitycontroler.views

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
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
import com.example.airqualitycontroler.adapters.AddStationsRecyclerAdapter
import com.example.airqualitycontroler.models.FavouriteId
import com.example.airqualitycontroler.models.Station
import com.example.airqualitycontroler.viewmodels.AddStationViewModel

class AddStationFragment : Fragment() {

    lateinit var myAdapter: AddStationsRecyclerAdapter
    lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: AddStationViewModel


    override fun onResume() {
        super.onResume()
        activity!!.setTitle("Add stations")
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
        //var stations = listOf<Station>()

        viewModel = ViewModelProviders.of(this).get(AddStationViewModel::class.java)
        viewModel.listOfStations.observe(viewLifecycleOwner, Observer<List<Station>> {
                t -> var stations=t
            var stationsName:List<String> = stations.map { it.city.name }
            autotextView.setAdapter(ArrayAdapter<String>(activity!!, android.R.layout.simple_list_item_1, stationsName.distinct()))
        })

        val button = getView()!!.findViewById<Button>(R.id.searchButton)
        if (button != null)
        {
            button ?.setOnClickListener(View.OnClickListener {
                viewModel.listOfStations.observe(viewLifecycleOwner, Observer<List<Station>> { t ->
                    myAdapter.setStationsInCity(t!!,autotextView.getText().toString())
                })

                viewModel.listFavId.observe(viewLifecycleOwner, Observer<List<FavouriteId>> { t ->
                    myAdapter.setFavId(t!!)
                })
            })
        }
        recyclerLoad()
    }

    private fun recyclerLoad() {
        myAdapter = AddStationsRecyclerAdapter {arg1,arg2 ->
            //arg1 -> id of clicked station
            //arg2 -> true - add to favouriteId, false - delete from favouriteId
            if(arg2)
            {
                viewModel.insert(FavouriteId(arg1.id))
                Toast.makeText(activity,"Dodano",Toast.LENGTH_SHORT).show()
            }else
            {
                viewModel.delete(FavouriteId(arg1.id))
                Toast.makeText(activity,"Usunięto",Toast.LENGTH_SHORT).show()
            }

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
