package com.example.juegodememoria

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import org.json.JSONArray
import org.json.JSONObject


class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        val sharedPreferences = Ranking(this);
        if(!sharedPreferences.getNombre().equals("top5")){
            crearArrayTop5();
        }
    }

    fun crearArrayTop5(){
        var array = JSONArray()
        var jugador1 = JSONObject()
        var jugador2 = JSONObject()
        var jugador3 = JSONObject()
        var jugador4 = JSONObject()
        var jugador5 = JSONObject()
        jugador1.put("Top 1","jugador 1");
        jugador1.put("movimiento", 50);
        array.put(jugador1);
        jugador2.put("Top 2","jugador 2");
        jugador2.put("movimiento", 40);
        array.put(jugador2);
        jugador3.put("Top 3","jugador 3");
        jugador3.put("movimiento", 50);
        array.put(jugador3);
        jugador4.put("Top 4","jugador 4");
        jugador4.put("movimiento", 20);
        array.put(jugador4);
        jugador5.put("Top 5","jugador 5");
        jugador5.put("movimiento", 10);
        array.put(jugador5);
        val sharedPreferences = Ranking(this);
        sharedPreferences.saveTop5Players(array);
    }
    fun comenzarJuegoBtn(v : View){
        val i = Intent(this, GameActivity::class.java);
        startActivity(i);
    }
    fun menuAyuda(v : View){
        val i = Intent(this, HelpActivity::class.java);
        startActivity(i);
    }
    fun ranking(v: View){
        val i = Intent(this,FinalActivity::class.java);
        startActivity(i);
    }
    fun salirJuego(v : View){
        dialogoSalir();
    }
    override fun onBackPressed() {
        dialogoSalir()
    }
    private fun dialogoSalir() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.ConfirmacionTitulo))
        builder.setPositiveButton(getString(R.string.aceptarSalir)) { dialog, _ ->
            finishAffinity()
        }
        builder.setNegativeButton(getString(R.string.cancelarSalir)) { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

}
