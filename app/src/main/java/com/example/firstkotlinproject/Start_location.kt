package com.example.firstkotlinproject

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationServices


class Start_location : AppCompatActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var tvLatitude: TextView
    private lateinit var tvLongitude: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_location)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        tvLatitude = findViewById(R.id.latitude)
        tvLongitude = findViewById(R.id.longitude)

        getCurrentLocation()
    }
    fun getCurrentLocation(){
        if(checkPermissions()){
             if(isLocationEnable()){
                 if (ActivityCompat.checkSelfPermission(
                         this,
                         Manifest.permission.ACCESS_FINE_LOCATION
                     ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                         this,
                         Manifest.permission.ACCESS_COARSE_LOCATION
                     ) != PackageManager.PERMISSION_GRANTED
                 ) {
                     // TODO: Consider calling
                     //    ActivityCompat#requestPermissions
                     // here to request the missing permissions, and then overriding
                     //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                     //                                          int[] grantResults)
                     // to handle the case where the user grants the permission. See the documentation
                     // for ActivityCompat#requestPermissions for more details.
                     return
                 }
                 fusedLocationProviderClient.lastLocation.addOnCompleteListener (this){ task ->
                    val location: Location?=task.result
                    if(location == null){
                        Toast.makeText(this, "NULL", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(this, "get success", Toast.LENGTH_SHORT).show()
                        tvLatitude.text=""+ location.latitude
                        tvLongitude.text=""+ location.latitude
                    }

                }
             }
            else{
                Toast.makeText(this, "Turn on location ", Toast.LENGTH_SHORT).show()

             }
        }
        else{
            requestPermissions()
        }
    }

    private fun isLocationEnable(): Boolean{
        val locationManager:LocationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }
    private fun requestPermissions(){
        ActivityCompat.requestPermissions(
            this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_REQUEST_ACCSESS_LOCATION
        )
    }

    companion object{
        private const val PERMISSION_REQUEST_ACCSESS_LOCATION = 100
    }
    private fun checkPermissions():Boolean {
        if(ActivityCompat.checkSelfPermission
                (this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
                    return true
        }

        return false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == PERMISSION_REQUEST_ACCSESS_LOCATION){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(applicationContext, "Granted", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(applicationContext, "deniede", Toast.LENGTH_LONG).show()
            }
        }
    }

}