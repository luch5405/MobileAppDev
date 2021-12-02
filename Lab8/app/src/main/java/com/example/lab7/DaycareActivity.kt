package com.example.lab7

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lab7.databinding.ActivityDaycareBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DaycareActivity : AppCompatActivity() {

    lateinit var daycareOutput: TextView
    lateinit var toMap: Button
    private var daycareName: String? = null
    private var daycareURL: String? = null
    private var daycareLngLat: String? = null

    private lateinit var binding: ActivityDaycareBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDaycareBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        //view
        daycareOutput = findViewById(R.id.daycareSug)
        toMap = findViewById(R.id.toMap)

            //intent
        daycareName = intent.getStringExtra("daycareName")
        daycareURL = intent.getStringExtra("daycareURL")
        daycareLngLat = intent.getStringExtra("daycareLngLat")
        daycareLngLat?.let { daycareOutput.text = daycareName }

        binding.fab.setOnClickListener { view -> loadWeb()}

        toMap.setOnClickListener {
            val mapIntent = Intent(this, MapsActivity::class.java)
            daycareLngLat?.let { it1 -> Log.i("lngLat", it1) }
            mapIntent.putExtra("daycareLngLat", daycareLngLat)
            startActivity(mapIntent)
        }


    }

    override fun onBackPressed() {
        var data = Intent()
        data.putExtra("daycareName", daycareOutput.text.toString())
        setResult(Activity.RESULT_OK,data)
        super.onBackPressed()
        finish()
    }

    private fun loadWeb(){
        var intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = daycareURL?.let{Uri.parse(daycareURL)}

        startActivity(intent)
    }
}