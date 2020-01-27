package com.example.airqualitycontroler.views

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.airqualitycontroler.R

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        //dodaenie fragmentu przy starcie
        supportFragmentManager.beginTransaction().add(R.id.container,StationsFragment()).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.getItemId()==R.id.action_add_station){
            supportFragmentManager.beginTransaction().replace(R.id.container,AddStationFragment())?.addToBackStack(null)?.commit()
        }
        return super.onOptionsItemSelected(item)
    }
}
