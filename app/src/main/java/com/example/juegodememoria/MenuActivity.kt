package com.example.juegodememoria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
    }

    fun comenzarJuegoBtn(v : View){
        val i = Intent(this, GameActivity::class.java);
        startActivity(i);
    }
    fun menuAyuda(v : View){
        val i = Intent(this, HelpActivity::class.java);
        startActivity(i);
    }
}
