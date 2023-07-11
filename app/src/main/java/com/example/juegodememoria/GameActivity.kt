package com.example.juegodememoria

import android.app.AlertDialog
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
import org.json.JSONException
import org.json.JSONObject



class GameActivity : AppCompatActivity() {
    private lateinit var contadorMov: TextView; // UTILIZADO PARA CONTAR JUGADAS
    private var sumarMovimientos = 0; // SUMA LOS MOVIMIENTOS PARA LUEGO INSERTARLOS EN EL contadorMov
    private lateinit var seleccionUno: Button; // UTILIZADO PARA GUARDAR VALOR DEL PRIMER CLICK
    private lateinit var seleccionDos: Button; // UTILIZADO PARA GUARDAR VALOR DEL SEGUNDO CLICK
    private var validadorClick = true; // VALIDAR QUE SE HAYA CLICKEADO 1ra O 2do VEZ
    private var cartasDescubiertas: MutableList<Int> = mutableListOf();
    private val PREFS_NAME = "partidaGuardada"
    private lateinit var partidaPrefs: SharedPreferences
    private val hashMap: HashMap<Int, Int> = HashMap()
    private val jsonObjectMap = JSONObject()
    private val jsonObjectList = JSONObject();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game);
        contadorMov = findViewById(R.id.txtContador);
        partidaPrefs = getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        var okPartida = partidaPrefs.getInt("hayPartida",3);
        var contadorShPref = partidaPrefs.getString("contador",null);
        if(okPartida == 1){
            Log.d("dentro del id CargarPart", " DENTRO DEL CARGAR PARTIDA")
            contadorMov.setText(contadorShPref)
            cargarPartida();
        }else {
            Log.d("dentro del else comenzarJuego", " DENTRO DEL COMENZAR JUEGO")
            comenzarJuego(findViewById(R.id.btnReiniciarComenzar));
        }

        var i = findViewById<ImageView>(R.id.ivVolver);
        i.setOnClickListener() {
            guardarPartida();
            val i = Intent(this, MenuActivity::class.java);
            i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);
        }
        val sharedPreferences : SharedPreferences = getSharedPreferences("Rank5", Context.MODE_PRIVATE);
        val top1 = sharedPreferences.getInt("mov1", 99);
        if(top1 == 0 || top1 == 99){
            var mostrarTop1 = findViewById<TextView>(R.id.movTop1).setText("-");
        }else{
            var mostrarTop1 = findViewById<TextView>(R.id.movTop1).setText(top1.toString());
        }
    }

    fun corrimientoRank(num : Int, mov : Int, nombre : String){
        val sharedPreferences : SharedPreferences = getSharedPreferences("Rank5", MODE_PRIVATE);
        var editor = sharedPreferences.edit()
        val top1Nombre = sharedPreferences.getString("Top 1","sin podio")
        val top1Mov =  sharedPreferences.getInt("mov1", -1);
        val top2Nombre = sharedPreferences.getString("Top 2","sin podio")
        val top2Mov  = sharedPreferences.getInt("mov2", -1);
        val top3Nombre = sharedPreferences.getString("Top 3","sin podio")
        val top3Mov  = sharedPreferences.getInt("mov3", -1);
        val top4Nombre = sharedPreferences.getString("Top 4","sin podio")
        val top4Mov  = sharedPreferences.getInt("mov4", -1);
        if(num == 1){
            editor.putString("Top 1", nombre);
            editor.putInt("mov1", mov);
            editor.putString("Top 2", top1Nombre);
            editor.putInt("mov2", top1Mov);
            editor.putString("Top 3", top2Nombre);
            editor.putInt("mov3", top2Mov);
            editor.putString("Top 4", top3Nombre);
            editor.putInt("mov4", top3Mov);
            editor.putString("Top 5", top4Nombre);
            editor.putInt("mov5", top4Mov);
            editor.commit();
        }else if (num == 2){
            editor.putString("Top 1", top1Nombre);
            editor.putInt("mov1", top1Mov);
            editor.putString("Top 2", nombre);
            editor.putInt("mov2", mov);
            editor.putString("Top 3", top2Nombre);
            editor.putInt("mov3", top2Mov);
            editor.putString("Top 4", top3Nombre);
            editor.putInt("mov4", top3Mov);
            editor.putString("Top 5", top4Nombre);
            editor.putInt("mov5", top4Mov);
            editor.commit();
        }else if(num == 3){
            editor.putString("Top 1", top1Nombre);
            editor.putInt("mov1", top1Mov);
            editor.putString("Top 2", top2Nombre);
            editor.putInt("mov2", top2Mov);
            editor.putString("Top 3", nombre);
            editor.putInt("mov3", mov);
            editor.putString("Top 4", top3Nombre);
            editor.putInt("mov4", top3Mov);
            editor.putString("Top 5", top4Nombre);
            editor.putInt("mov5", top4Mov);
            editor.commit();
        }else if (num == 4){
            editor.putString("Top 1", top1Nombre);
            editor.putInt("mov1", top1Mov);
            editor.putString("Top 2", top2Nombre);
            editor.putInt("mov2", top2Mov);
            editor.putString("Top 3", top3Nombre);
            editor.putInt("mov3", top3Mov);
            editor.putString("Top 4", nombre);
            editor.putInt("mov4", mov);
            editor.putString("Top 5", top4Nombre);
            editor.putInt("mov5", top4Mov);
            editor.commit();
        }else if(num == 5){
            editor.putString("Top 1", top1Nombre);
            editor.putInt("mov1", top1Mov);
            editor.putString("Top 2", top2Nombre);
            editor.putInt("mov2", top2Mov);
            editor.putString("Top 3", top3Nombre);
            editor.putInt("mov3", top3Mov);
            editor.putString("Top 4", top4Nombre);
            editor.putInt("mov4", top4Mov);
            editor.putString("Top 5", nombre);
            editor.putInt("mov5", mov);
            editor.commit();
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
            guardarPartida();
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
                }
            }else {
                    i2.putExtra(Intent.EXTRA_TEXT, "Tu Puntaje es: " + contadorMov.getText());
                    if (i2.resolveActivity(packageManager) != null) {
                        startActivity(i2);
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
        var editorClear = partidaPrefs.edit();
        editorClear.clear();
        editorClear.commit();
        hashMap.clear();
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
                Log.d("Dentro prepararJuego", numero.toString())
                hashMap[cartaAleatoria] = numero;
                carta.isEnabled = true;
            }
            for ((key, value) in hashMap) {
                jsonObjectMap.put(key.toString(),value)
            }
            val editor = partidaPrefs.edit()
            editor.putString("hashMapCartas", jsonObjectMap.toString())
            editor.commit()
        }
    }
    fun cargarPartida(){
        Log.d("CARGAR PARTIDA FUNCION", " CARGAR PARTIDA FUNCION")
        val jsonString: String? = partidaPrefs.getString("hashMapCartas", null);
        val hashMap: HashMap<String, Int> = HashMap()
        if (!jsonString.isNullOrEmpty()) {
            try {
                val jsonObject = JSONObject(jsonString)
                val keys = jsonObject.keys()
                while (keys.hasNext()) {
                    val key = keys.next()
                    val value = jsonObject.getInt(key)
                    hashMap[key] = value
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        val json = partidaPrefs.getString("ListaDescubierta", null)
        if (json != null) {
            Log.d("JSON NO ES NULL", "JSON NO ES NULL")
            var jsonOb = JSONObject(json);
            val keys = jsonOb.keys()
            keys.forEach { s ->
                cartasDescubiertas.add(s.toInt())
                Log.d("FOR EACH", "FOR EACH")
            }
        }
        for ((clave, valor) in hashMap) {
            if (cartasDescubiertas.contains(clave.toInt())) {
                var btn = findViewById<Button>(clave.toInt());
                Log.d("valor del hasmap if",hashMap[clave].toString())
                btn.setText(valor.toString());
                btn.setBackgroundColor(Color.parseColor("#008F39")) // VERDE
                btn.isEnabled = false;
            } else {
                var btn = findViewById<Button>(clave.toInt());
                Log.d("valor hasmap else", hashMap[clave].toString())
                btn.setText(valor.toString());
                btn.setBackgroundColor(Color.parseColor("#000000")) // NEGRO
            }
        }
//        for (element in cartasDescubiertas) {
//            if (cartasDescubiertas.contains(clave)) {
//                var btn = findViewById<Button>(element);
//                Log.d("valor del hasmap if",hashMap[element.toString()].toString())
//                btn.setText(hashMap[element.toString()].toString());
//                btn.setBackgroundColor(Color.parseColor("#008F39")) // VERDE
//                btn.isEnabled = false;
//            } else {
//                var btn = findViewById<Button>(element);
//                Log.d("valor hasmap else", hashMap[element.toString()].toString())
//                btn.setText(hashMap[element.toString()].toString());
//                btn.setBackgroundColor(Color.parseColor("#000000")) // NEGRO
//            }
//        }
    }
    fun guardarPartida(){
        for (i in cartasDescubiertas) {
            jsonObjectList.put(i.toString(),"Hola")
        }
        var editor = partidaPrefs.edit();
        editor.putString("ListaDescubierta", jsonObjectList.toString());
        editor.putInt("hayPartida",1);
        Log.d("dentro del guardar partida", " DENTRO DEL GUARDAR PARTIDA")
        editor.putString("contador",contadorMov.getText().toString());
        editor.commit();
    }
//    fun tarjetasAShared(){
//       var editor = partidaPrefs.edit();
//       editor.putString("tarjeta1","2131231180")
//        editor.putString("tarjeta2","2131231188")
//        editor.putString("tarjeta3","2131231189")
//        editor.putString("tarjeta4","2131231190")
//        editor.putString("tarjeta5","2131231191")
//        editor.putString("tarjeta6","2131231192")
//        editor.putString("tarjeta7","2131231193")
//        editor.putString("tarjeta8","2131231194")
//        editor.putString("tarjeta9","2131231195")
//        editor.putString("tarjeta10","2131231181")
//        editor.putString("tarjeta11","2131231182")
//        editor.putString("tarjeta12","2131231183")
//        editor.putString("tarjeta13","2131231184")
//        editor.putString("tarjeta14","2131231185")
//        editor.putString("tarjeta15","2131231186")
//        editor.putString("tarjeta16","2131231187")
//        editor.commit();
//    }

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
        val sharedPreferences : SharedPreferences = getSharedPreferences("Rank5", Context.MODE_PRIVATE);
        val top1Mov =  sharedPreferences.getInt("mov1", -1);
        val top2Mov  = sharedPreferences.getInt("mov2", -1);
        val top3Mov  = sharedPreferences.getInt("mov3", -1);
        val top4Mov  = sharedPreferences.getInt("mov4", -1);
        val top5Mov  = sharedPreferences.getInt("mov5", -1);
        if(top1Mov == 0 || top1Mov > mov){
            return 1;
        }else if(top2Mov == 0 || top2Mov > mov){
            return 2;
        }else if(top3Mov == 0 || top3Mov > mov){
            return 3;
        }else if(top4Mov == 0 || top4Mov > mov){
            return 4;
        }else if(top5Mov == 0 || top5Mov > mov){
            return 5;
        }
        else{
            return 0;
        }
    }
    fun addCartasAlista(uno : Int, dos: Int){
        cartasDescubiertas.add(uno);
        cartasDescubiertas.add(dos);
        if(cartasDescubiertas.size == 16) {     // PONER EN 2 PARA ACELERAR EL JUEGO
            val posicion = buscarPos(sumarMovimientos)
            if(posicion != 0){
                val input = EditText(this)
                val builder = AlertDialog.Builder(this)
                    .setMessage(getString(R.string.nuevoRecord))
                    .setView(input)
                    .setPositiveButton(getString(R.string.aceptar)) { dialog,which ->
                        corrimientoRank(buscarPos(sumarMovimientos),sumarMovimientos,input.text.toString());
                        val builder2 = AlertDialog.Builder(this)
                            .setMessage(R.string.Que_quieres_hacer)
                            .setPositiveButton(R.string.Volver_a_Jugar) { dialog,which ->
                                if(posicion == 1){
                                    var mostrarTop1 = findViewById<TextView>(R.id.movTop1).setText(sumarMovimientos.toString())
                                }
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
                                startActivity(i);
                            }
                        val dialog2 = builder2.create();
                        dialog2.setCancelable(false);
                        dialog2.show();
                    }
                val dialog = builder.create();
                dialog.setCancelable(false);
                dialog.show();
            }else{
                val builder2 = AlertDialog.Builder(this)
                    .setMessage(R.string.Que_quieres_hacer)
                    .setPositiveButton(R.string.Volver_a_Jugar) { dialog,which ->
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
                        startActivity(i);
                    }
                val dialog2 = builder2.create();
                dialog2.setCancelable(false);
                dialog2.show();
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
            var tomarMov = partidaPrefs.getString("contador","0")
            if(tomarMov == "0"){
                sumarMovimientos++;
            }else{
                if(sumarMovimientos == 0){
                    sumarMovimientos++
                }
                sumarMovimientos = sumarMovimientos + tomarMov!!.toInt();
                var editor = partidaPrefs.edit();
                editor.putString("contador","0");
                editor.commit();
            }
            contadorMov.setText(sumarMovimientos.toString());
        }
    }
}


