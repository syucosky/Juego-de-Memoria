package com.example.juegodememoria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class HelpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        if(getIntent().getStringExtra("juego").equals("desdeElJuego")){
            var btnComenzar = findViewById<Button>(R.id.btnComenzarDesdeAyuda);
            btnComenzar.visibility = View.GONE;
        }
    }
    fun comenzarJuegoBtn(v : View){
        val i = Intent(this, GameActivity::class.java);
        i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(i);
    }

    fun volver(v : View){
        super.onBackPressed();
        finish();
    }
}