package com.example.juegodememoria

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import org.json.JSONObject

class FinalActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)
        val sharedPreferences = Ranking(this);
        val top5FromPrefs = sharedPreferences.getTop5Players()
        val top1Nombre = findViewById<TextView>(R.id.nombreTopUno).setText(top5FromPrefs.getJSONObject(0).getString("Top 1"))
        val top1Mov = findViewById<TextView>(R.id.movTopUno).setText(top5FromPrefs.getJSONObject(0).getInt("movimiento").toString())
        val top2Nombre = findViewById<TextView>(R.id.nombreTopDos).setText(top5FromPrefs.getJSONObject(1).getString("Top 2"))
        val top2Mov = findViewById<TextView>(R.id.movTopDos).setText(top5FromPrefs.getJSONObject(1).getInt("movimiento").toString())
        val top3Nombre = findViewById<TextView>(R.id.nombreTopTres).setText(top5FromPrefs.getJSONObject(2).getString("Top 3"))
        val top3Mov = findViewById<TextView>(R.id.movTopTres).setText(top5FromPrefs.getJSONObject(2).getInt("movimiento").toString())
        val top4Nombre = findViewById<TextView>(R.id.nombreTopCuatro).setText(top5FromPrefs.getJSONObject(3).getString("Top 4"))
        val top4Mov = findViewById<TextView>(R.id.movTopCuatro).setText(top5FromPrefs.getJSONObject(3).getInt("movimiento").toString())
        val top5Nombre = findViewById<TextView>(R.id.nombreTopCinco).setText(top5FromPrefs.getJSONObject(4).getString("Top 5"))
        val top5Mov = findViewById<TextView>(R.id.movTopCinco).setText(top5FromPrefs.getJSONObject(4).getInt("movimiento").toString())

    }

    fun comenzarJuego(v : View){
        val i = Intent(this, GameActivity::class.java);
        startActivity(i);
    }
    fun volver( v : View){
        finish();
    }
}