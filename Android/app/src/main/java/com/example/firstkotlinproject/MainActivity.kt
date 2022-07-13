package com.example.firstkotlinproject

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager

import java.net.URI
var music = MediaPlayer()
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        music.setDataSource(this, Uri.parse("android.resource://" +this.packageName+"/"+R.raw.music))
        music.start()
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main) // Damit geben wir an das die main_activity angegeben werden soll
    }

    fun openSettings(view: View){
        val cangePage = Intent(this, Settings::class.java)
        startActivity(cangePage)
    }
    fun openHelp(view: View){
        val cangePage = Intent(this, Help::class.java)
        startActivity(cangePage)
    }

    fun openStart(view: View){
        val cangePage = Intent(this, Start_location::class.java)
        startActivity(cangePage)
    }
}