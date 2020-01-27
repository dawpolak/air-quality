package com.example.airqualitycontroler.views

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.example.airqualitycontroler.R
import com.example.airqualitycontroler.adapters.StationsDetailsRecyclerAdapter
import com.example.airqualitycontroler.models.SensorsInStation
import com.example.airqualitycontroler.models.Station
import com.example.airqualitycontroler.models.StationWithSensors
import com.example.airqualitycontroler.viewmodels.StationsViewModel

class StationsFragment : Fragment() {

    lateinit var myAdapter: StationsDetailsRecyclerAdapter
    lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: StationsViewModel
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.stations_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()
        activity!!.setTitle("Yours stations")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView = getView()!!.findViewById(R.id.stations_recycler)

        viewModel = ViewModelProviders.of(this).get(StationsViewModel::class.java)

        recyclerLoad()
        setUpUi()
        fetchUsers()
    }

    private fun setUpUi() {
        swipeRefreshLayout = getView()!!.findViewById(R.id.swipeRefresh)
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshUsers()
        }
    }

    private fun fetchUsers() {
        swipeRefreshLayout.isRefreshing = true
        viewModel.listOfFavStationsWithSensors.observe(viewLifecycleOwner, Observer { station -> station?.let { displayUsers(it) } })
    }

    private fun displayUsers(stations: List<StationWithSensors>) {
        swipeRefreshLayout.isRefreshing = false

        //get data from viewModel and put them to RecyclerAdapter
        viewModel.listOfFavStationsWithSensors.observe(viewLifecycleOwner, Observer<List<StationWithSensors>> {
                t -> myAdapter.setStationsWithSensors(t!!)
        })
//        viewModel.listOfFavStations.observe(viewLifecycleOwner, Observer<List<Station>> {
//                t -> myAdapter.setStations(t!!)
//        })

    }

    private fun recyclerLoad() {
        myAdapter = StationsDetailsRecyclerAdapter {
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
