package com.example.firstkotlinproject

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task


class Start_location : AppCompatActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var latitude: TextView
    private lateinit var longitude: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_location)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        latitude = findViewById(R.id.latitude)
        longitude = findViewById(R.id.longitude)
        val button = findViewById<Button>(R.id.location_button)

        button.setOnClickListener{
            getLocation()
        }

    }

    private fun getLocation(){
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this,
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 100)
                    return
        }

        val location = fusedLocationProviderClient.lastLocation
        location.addOnSuccessListener {
            if(it != null){
                val textLatitude = "Latetidude: " + it.latitude.toString()
                val textLongidue = "Longitude: " + it.longitude.toString()
                latitude.text = textLatitude
                longitude.text = textLongidue
                Log.i("Übergabe", "drinne")
            }
            else if(it == null) {
                Log.i("Übergabe", "draussen")
            }
        }
    }


}