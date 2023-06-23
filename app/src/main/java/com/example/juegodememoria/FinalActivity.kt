package com.example.juegodememoria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class FinalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

        var mov = findViewById<TextView>(R.id.cantMov);
        mov.setText(getIntent().getStringExtra("movimientos"))

        var porcen = findViewById<TextView>(R.id.porcTv);
        porcen.setText(getIntent().getStringExtra("porcentaje"))


    }
    fun comenzarJuego(v : View){
        val i = Intent(this, GameActivity::class.java);
        startActivity(i);
    }
}