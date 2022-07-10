package com.example.firstkotlinproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main) // Damit geben wir an das die main_activity angegeben werden soll
        supportActionBar?.hide()
    }

    fun openSettings(view: View){
        val cangePage = Intent(this, Settings::class.java)
        startActivity(cangePage)
    }
    fun openHelp(view: View){
        val cangePage = Intent(this, Help::class.java)
        startActivity(cangePage)
    }
}