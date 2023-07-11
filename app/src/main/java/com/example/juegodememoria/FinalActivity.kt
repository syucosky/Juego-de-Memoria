package com.example.juegodememoria

import android.content.Context
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
          val pref : SharedPreferences = getSharedPreferences("Rank5", Context.MODE_PRIVATE);
          var top1 = pref.getString("Top 1", "sin podio");
          var mov1 = pref.getInt("mov1", 0);
          var top2 = pref.getString("Top 2", "sin podio");
          var mov2 = pref.getInt("mov2", 0);
          var top3 = pref.getString("Top 3", "sin podio");
          var mov3 = pref.getInt("mov3", 0);
          var top4 = pref.getString("Top 4", "sin podio");
          var mov4 = pref.getInt("mov4", 0);
          var top5 = pref.getString("Top 5", "sin podio");
          var mov5 = pref.getInt("mov5", 0);
          val top1Nombre = findViewById<TextView>(R.id.nombreTopUno).setText(top1)
          val top1Mov = findViewById<TextView>(R.id.movTopUno).setText(mov1.toString())
          val top2Nombre = findViewById<TextView>(R.id.nombreTopDos).setText(top2)
          val top2Mov = findViewById<TextView>(R.id.movTopDos).setText(mov2.toString())
          val top3Nombre = findViewById<TextView>(R.id.nombreTopTres).setText(top3)
          val top3Mov = findViewById<TextView>(R.id.movTopTres).setText(mov3.toString())
          val top4Nombre = findViewById<TextView>(R.id.nombreTopCuatro).setText(top4)
          val top4Mov = findViewById<TextView>(R.id.movTopCuatro).setText(mov4.toString())
          val top5Nombre = findViewById<TextView>(R.id.nombreTopCinco).setText(top5)
          val top5Mov = findViewById<TextView>(R.id.movTopCinco).setText(mov5.toString())
    }

    fun comenzarJuego(v : View){
        val i = Intent(this, GameActivity::class.java);
        i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(i);
    }
    fun volver( v : View){
        val i = Intent(this, MenuActivity::class.java);
        i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(i);
    }
}