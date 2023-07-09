package com.example.juegodememoria

import android.app.AlertDialog
import android.app.Dialog
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.core.os.postDelayed
import org.json.JSONArray
import org.json.JSONObject


class GameActivity : AppCompatActivity() {
    private lateinit var contadorMov: TextView; // UTILIZADO PARA CONTAR JUGADAS
    private var sumarMovimientos = 0; // SUMA LOS MOVIMIENTOS PARA LUEGO INSERTARLOS EN EL contadorMov
    private lateinit var seleccionUno: Button; // UTILIZADO PARA GUARDAR VALOR DEL PRIMER CLICK
    private lateinit var seleccionDos: Button; // UTILIZADO PARA GUARDAR VALOR DEL SEGUNDO CLICK
    private var validadorClick = true; // VALIDAR QUE SE HAYA CLICKEADO 1ra O 2do VEZ
    private var cartasDescubiertas: MutableList<Int> = mutableListOf();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game);
        contadorMov = findViewById(R.id.txtContador);
        comenzarJuego(findViewById(R.id.btnReiniciarComenzar));
        var i = findViewById<ImageView>(R.id.ivVolver);
        i.setOnClickListener() {
            val i = Intent(this, MenuActivity::class.java);
            i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);
        }
        val sharedPreferences = Ranking(this);
        val top5FromPrefs = sharedPreferences.getTop5Players()
        var mostrarTop1 = findViewById<TextView>(R.id.movTop1).setText( top5FromPrefs.getJSONObject(0).getInt("movimiento").toString());
    }
    fun corrimientoRank(num : Int, mov : Int, nombre : String, pref : Ranking){
        var top = pref.getTop5Players();
        var array = JSONArray()
        var jugador1 = JSONObject()
        var jugador2 = JSONObject()
        var jugador3 = JSONObject()
        var jugador4 = JSONObject()
        var jugador5 = JSONObject()
        if(num == 1){
            jugador1.put("Top 1",nombre);
            jugador1.put("movimiento", mov);
            array.put(jugador1);
            jugador2.put("Top 2",top.getJSONObject(0).getString("Top 1"));
            jugador2.put("movimiento", top.getJSONObject(0).getInt("movimiento"));
            array.put(jugador2);
            jugador3.put("Top 3",top.getJSONObject(1).getString("Top 2"));
            jugador3.put("movimiento", top.getJSONObject(1).getInt("movimiento"));
            array.put(jugador3);
            jugador4.put("Top 4",top.getJSONObject(2).getString("Top 3"));
            jugador4.put("movimiento", top.getJSONObject(2).getInt("movimiento"));
            array.put(jugador4);
            jugador5.put("Top 5",top.getJSONObject(3).getString("Top 4"));
            jugador5.put("movimiento", top.getJSONObject(3).getInt("movimiento"));
            array.put(jugador5);
            val sharedPreferences = Ranking(this);
            sharedPreferences.saveTop5Players(array);
        }else if (num == 2){
            jugador1.put("Top 1",top.getJSONObject(0).getString("Top 1"));
            jugador1.put("movimiento", top.getJSONObject(0).getInt("movimiento"));
            array.put(jugador1);
            jugador2.put("Top 2",nombre);
            jugador2.put("movimiento", mov);
            array.put(jugador2);
            jugador3.put("Top 3",top.getJSONObject(1).getString("Top 2"));
            jugador3.put("movimiento", top.getJSONObject(1).getInt("movimiento"));
            array.put(jugador3);
            jugador4.put("Top 4",top.getJSONObject(2).getString("Top 3"));
            jugador4.put("movimiento", top.getJSONObject(2).getInt("movimiento"));
            array.put(jugador4);
            jugador5.put("Top 5",top.getJSONObject(3).getString("Top 4"));
            jugador5.put("movimiento", top.getJSONObject(3).getInt("movimiento"));
            array.put(jugador5);
            val sharedPreferences = Ranking(this);
            sharedPreferences.saveTop5Players(array);
        }else if(num == 3){
            jugador1.put("Top 1",top.getJSONObject(0).getString("Top 1"));
            jugador1.put("movimiento", top.getJSONObject(0).getInt("movimiento"));
            array.put(jugador1);
            jugador2.put("Top 2",top.getJSONObject(1).getString("Top 2"));
            jugador2.put("movimiento", top.getJSONObject(1).getInt("movimiento"));
            array.put(jugador2);
            jugador3.put("Top 3",nombre);
            jugador3.put("movimiento", mov);
            array.put(jugador3);
            jugador4.put("Top 4",top.getJSONObject(2).getString("Top 3"));
            jugador4.put("movimiento", top.getJSONObject(2).getInt("movimiento"));
            array.put(jugador4);
            jugador5.put("Top 5",top.getJSONObject(3).getString("Top 4"));
            jugador5.put("movimiento", top.getJSONObject(3).getInt("movimiento"));
            array.put(jugador5);
            val sharedPreferences = Ranking(this);
            sharedPreferences.saveTop5Players(array);
        }else if (num == 4){
            jugador1.put("Top 1",top.getJSONObject(0).getString("Top 1"));
            jugador1.put("movimiento", top.getJSONObject(0).getInt("movimiento"));
            array.put(jugador1);
            jugador2.put("Top 2",top.getJSONObject(1).getString("Top 2"));
            jugador2.put("movimiento", top.getJSONObject(1).getInt("movimiento"));
            array.put(jugador2);
            jugador3.put("Top 3",top.getJSONObject(2).getString("Top 3"));
            jugador3.put("movimiento", top.getJSONObject(2).getInt("movimiento"));
            array.put(jugador3);
            jugador4.put("Top 4",nombre);
            jugador4.put("movimiento", mov);
            array.put(jugador4);
            jugador5.put("Top 5",top.getJSONObject(3).getString("Top 4"));
            jugador5.put("movimiento", top.getJSONObject(3).getInt("movimiento"));
            array.put(jugador5);
            val sharedPreferences = Ranking(this);
            sharedPreferences.saveTop5Players(array);
        }else if(num == 5){
            jugador1.put("Top 1",top.getJSONObject(0).getString("Top 1"));
            jugador1.put("movimiento", top.getJSONObject(0).getInt("movimiento"));
            array.put(jugador1);
            jugador2.put("Top 2",top.getJSONObject(1).getString("Top 2"));
            jugador2.put("movimiento", top.getJSONObject(1).getInt("movimiento"));
            array.put(jugador2);
            jugador3.put("Top 3",top.getJSONObject(2).getString("Top 3"));
            jugador3.put("movimiento", top.getJSONObject(2).getInt("movimiento"));
            array.put(jugador3);
            jugador4.put("Top 4",top.getJSONObject(3).getString("Top 4"));
            jugador4.put("movimiento", top.getJSONObject(3).getInt("movimiento"));
            array.put(jugador4);
            jugador5.put("Top 5",nombre);
            jugador5.put("movimiento", mov);
            array.put(jugador5);
            val sharedPreferences = Ranking(this);
            sharedPreferences.saveTop5Players(array);
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val mi: MenuInflater = menuInflater;
        mi.inflate(R.menu.menu_game, menu);
        return true;

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuAyuda){
            val i = Intent(this, HelpActivity::class.java);
            i.putExtra("juego","desdeElJuego");
            startActivity(i);
        }
        if(item.itemId == R.id.reiniciarJuego){
            prepararJuego(); // SE ENCARGA DE ELEGIR E INSERTAR LOS NUMEROS EN LAS CARTAS ALEATORIAMENTE
            sumarMovimientos = 0
            contadorMov.setText(sumarMovimientos.toString());
            cartasDescubiertas.clear();
        }
        if(item.itemId == R.id.volverAlMenu){
            val i = Intent(this, MenuActivity::class.java);
            i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);
        }
        if(item.itemId == R.id.compartirPuntaje){
            val i2 = Intent();
            i2.action = Intent.ACTION_SEND;
            i2.type= "text/plain";
            if(item.title!!.equals("Share Score")){
                i2.putExtra(Intent.EXTRA_TEXT,"Your Score is: " + contadorMov.getText());
                if (i2.resolveActivity(packageManager) != null){
                    startActivity(i2);
                    Log.d(applicationContext.toString(),"ingles");
                }
            }else {
                    i2.putExtra(Intent.EXTRA_TEXT, "Tu Puntaje es: " + contadorMov.getText());
                    if (i2.resolveActivity(packageManager) != null) {
                        startActivity(i2);
                        Log.d(applicationContext.toString(),"espanol");
                    }
            }
        }
        return false;

    }


    fun comenzarJuego(v: View) {
        prepararJuego(); // SE ENCARGA DE ELEGIR E INSERTAR LOS NUMEROS EN LAS CARTAS ALEATORIAMENTE
        sumarMovimientos = 0
        contadorMov.setText(sumarMovimientos.toString());
        cartasDescubiertas.clear();
    }


    fun onClick(v: View) {
        buscandoPares(v.getId()); // SE ENCARGA DE DESCUBRIR LAS CARTAS SI HAY PARIDAD DE NUMERO
    }

    fun prepararJuego() {
        val cartas = mutableListOf(
            R.id.tarjeta1, R.id.tarjeta2, R.id.tarjeta3, R.id.tarjeta4,
            R.id.tarjeta5, R.id.tarjeta6, R.id.tarjeta7, R.id.tarjeta8,
            R.id.tarjeta9, R.id.tarjeta10, R.id.tarjeta11, R.id.tarjeta12,
            R.id.tarjeta13, R.id.tarjeta14, R.id.tarjeta15, R.id.tarjeta16
        ); // LISTA MUTABLE CON LOS ID`S DE LAS CARTAS
        for (numero in 1..8) { // ESTRUCTURA PARA ELEGIR NUMERO Y POSICION DENTRO DEL TABLERO
            for (y in 1..2) {
                var cartaAleatoria = cartas.random(); // SELECCIONA CARTA ALEATORIA
                cartas.remove(cartaAleatoria); // SACA DE LA LISTA LA CARTA SELECCIONADA
                var carta =findViewById<Button>(cartaAleatoria); // SELECCIONA A TRAVES DEL ID LA CARTA
                carta.setText(numero.toString()); // INSERTA EL NUMERO EN LA CARTA
                carta.setBackgroundColor(Color.parseColor("#000000"));
                carta.isEnabled = true;
            }

        }
    }

    fun bloquearBotones(ok: Boolean) {
        var cartas = mutableListOf(
            R.id.tarjeta1, R.id.tarjeta2, R.id.tarjeta3, R.id.tarjeta4,
            R.id.tarjeta5, R.id.tarjeta6, R.id.tarjeta7, R.id.tarjeta8,
            R.id.tarjeta9, R.id.tarjeta10, R.id.tarjeta11, R.id.tarjeta12,
            R.id.tarjeta13, R.id.tarjeta14, R.id.tarjeta15, R.id.tarjeta16
        );
        for (id in cartas) {
            var button = findViewById<Button>(id);
            button.isEnabled = ok; //SEGUN SEA TRUE O FALSE DESBLOQUEA O BLOQUEA BOTON
        }
    }
    fun desbloquearBotonesPorLista(ok: Boolean, lista : Collection<Int>){
        var cartas = mutableListOf(
            R.id.tarjeta1, R.id.tarjeta2, R.id.tarjeta3, R.id.tarjeta4,
            R.id.tarjeta5, R.id.tarjeta6, R.id.tarjeta7, R.id.tarjeta8,
            R.id.tarjeta9, R.id.tarjeta10, R.id.tarjeta11, R.id.tarjeta12,
            R.id.tarjeta13, R.id.tarjeta14, R.id.tarjeta15, R.id.tarjeta16
        );
        cartas.removeAll(lista);
        for (id in cartas) {
            var button = findViewById<Button>(id);
            button.isEnabled = ok; //SEGUN SEA TRUE O FALSE DESBLOQUEA O BLOQUEA BOTON
        }
    }
    fun buscarPos(mov : Int):Int{
        val sharedPreferences = Ranking(this);
        val top5FromPrefs = sharedPreferences.getTop5Players()
        val top1Mov =  top5FromPrefs.getJSONObject(0).getInt("movimiento")
        val top2Mov  = top5FromPrefs.getJSONObject(1).getInt("movimiento")
        val top3Mov  = top5FromPrefs.getJSONObject(2).getInt("movimiento")
        val top4Mov  = top5FromPrefs.getJSONObject(3).getInt("movimiento")
        val top5Mov  = top5FromPrefs.getJSONObject(4).getInt("movimiento")
        if(top1Mov > mov){
            return 1;
        }else if(top2Mov > mov){
            return 2;
        }else if(top3Mov > mov){
            return 3;
        }else if(top4Mov > mov){
            return 4;
        }else if(top5Mov > mov){
            return 5;
        }
        else{
            return 0;
        }
    }
    fun addCartasAlista(uno : Int, dos: Int){
        cartasDescubiertas.add(uno);
        cartasDescubiertas.add(dos);
        if(cartasDescubiertas.size == 2) {
            val sharedPreferences = Ranking(this);
            val posicion = buscarPos(sumarMovimientos)
            if(posicion != 0){
                val input = EditText(this)
                val builder = AlertDialog.Builder(this)
                    .setMessage(getString(R.string.nuevoRecord))
                    .setView(input)
                    .setPositiveButton(getString(R.string.aceptar)) { dialog,which ->
                        corrimientoRank(buscarPos(sumarMovimientos),sumarMovimientos,input.text.toString(),sharedPreferences);
                        prepararJuego(); // SE ENCARGA DE ELEGIR E INSERTAR LOS NUMEROS EN LAS CARTAS ALEATORIAMENTE
                        sumarMovimientos = 0
                        contadorMov.setText(sumarMovimientos.toString());
                        cartasDescubiertas.clear();
                    }
                    .setNegativeButton(getString(R.string.compartir)) { dialog, which ->
                        val i2 = Intent();
                        i2.action = Intent.ACTION_SEND;
                        i2.type= "text/plain";
                        if(R.string.compartir.equals("Share")){
                            i2.putExtra(Intent.EXTRA_TEXT,"Your Score is: " + contadorMov.getText());
                            if (i2.resolveActivity(packageManager) != null){
                                startActivity(i2);
                            }
                        }else {
                            i2.putExtra(Intent.EXTRA_TEXT, "Tu Puntaje es: " + contadorMov.getText());
                            if (i2.resolveActivity(packageManager) != null) {
                                startActivity(i2);
                            }
                        }

                    }
                    .setNeutralButton(R.string.Podio){ dialog, which ->
                        val i = Intent(this,FinalActivity::class.java);
                        i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(i);
                    }
                val dialog = builder.create();
                dialog.show();
            }else{

            }



            }
        }
    fun buscandoPares(valorClick: Int) {
        if(validadorClick && !cartasDescubiertas.contains(valorClick) && !valorClick.equals(R.id.btnReiniciarComenzar)){
            seleccionUno = findViewById(valorClick);
            seleccionUno.setBackgroundColor(Color.parseColor("#FFFFFF"))
            validadorClick = false;
            seleccionUno.isEnabled = false;
        }else if(!validadorClick.toString().equals(seleccionUno) && !cartasDescubiertas.contains(valorClick) && !valorClick.equals(R.id.btnReiniciarComenzar)){
            seleccionDos = findViewById(valorClick);
            seleccionDos.setBackgroundColor(Color.parseColor("#FFFFFF"));
            validadorClick = true;
            seleccionDos.isEnabled = false;
        }
        if(validadorClick){
            bloquearBotones(false);
            if(seleccionUno.getText().equals(seleccionDos.getText())){
                addCartasAlista(seleccionUno.getId(),seleccionDos.getId()); // AGREGO LAS CARTAS  A LA LISTA PARA DSP NO DESBLOQUEARLAS EN LA LINEA 119
                Handler().postDelayed(500) {
                    seleccionUno.setBackgroundColor(Color.parseColor("#008F39")); //VERDE
                    seleccionDos.setBackgroundColor(Color.parseColor("#008F39"));
                }
            }else{
                Handler().postDelayed(500) {
                    seleccionUno.setBackgroundColor(Color.parseColor("#B84616")); // ROJO
                    seleccionDos.setBackgroundColor(Color.parseColor("#B84616"));
                }
                Handler().postDelayed(1000) {
                    seleccionUno.setBackgroundColor(Color.parseColor("#000000"));
                    seleccionDos.setBackgroundColor(Color.parseColor("#000000"));
                }
            }
            Handler().postDelayed(1100) {
                desbloquearBotonesPorLista(true,cartasDescubiertas);
            }
            sumarMovimientos++;
            contadorMov.setText(sumarMovimientos.toString());
        }
    }
}


