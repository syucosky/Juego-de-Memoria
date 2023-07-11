package com.example.juegodememoria

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import org.json.JSONArray
import org.json.JSONObject


class MenuActivity : AppCompatActivity() {
    private val PREFS_NAME = "Rank5"
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        val contiene = sharedPreferences.getInt("cargado", 3);
        if(contiene == 3){
            Log.d("contiene","mensaje if de contiene")
            crearArrayTop5(sharedPreferences);
        }else{
            Log.d("no contiene","mensaje else de contiene")
        }
    }

    fun crearArrayTop5(sharedPreferences : SharedPreferences){
        var editor = sharedPreferences.edit()
        editor.putString("Top 1", "");
        editor.putInt("mov1", 0);
        editor.putString("Top 2", "");
        editor.putInt("mov2", 0);
        editor.putString("Top 3", "");
        editor.putInt("mov3", 0);
        editor.putString("Top 4", "");
        editor.putInt("mov4", 0);
        editor.putString("Top 5", "");
        editor.putInt("mov5", 0);
        editor.putInt("cargado", 1);
        editor.commit();
    }
    fun comenzarJuegoBtn(v : View){
        val i = Intent(this, GameActivity::class.java);
        i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
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
