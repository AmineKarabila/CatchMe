package com.example.firstkotlinproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager

class Help : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main) // Damit geben wir an das die main_activity angegeben werden soll
        supportActionBar?.hide()
        setContentView(R.layout.activity_help)
    }

    fun openMain(view: View){
        val cangePage = Intent(this, MainActivity::class.java)
        startActivity(cangePage)
    }
}