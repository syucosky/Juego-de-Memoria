  package com.example.juegodememoria

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.postDelayed


  class MainActivity : AppCompatActivity(){
    private lateinit var contadorMov : TextView; // UTILIZADO PARA CONTAR JUGADAS
    private var sumarMovimientos = 0; // SUMA LOS MOVIMIENTOS PARA LUEGO INSERTARLOS EN EL contadorMov
    private var valorClick = 0; // UTILIZADO PARA PASAR EL ID DE LOS BOTONES CLICKEADOS
    private  lateinit var seleccionUno : Button; // UTILIZADO PARA GUARDAR VALOR DEL PRIMER CLICK
    private  lateinit var seleccionDos : Button; // UTILIZADO PARA GUARDAR VALOR DEL SEGUNDO CLICK
    private var validadorClick = true; // VALIDAR QUE SE HAYA CLICKEADO 1 O 2 VECES
    private var juegoComenzo = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        contadorMov = findViewById(R.id.txtContador);

    }
    fun comenzarJuego(v: View){
        val btnComRei : Button = findViewById(R.id.btnReiniciarComenzar)
        comenzarReiniciarJuego(btnComRei); // SE ENCARGA DE REINICIAR/COMENZAR EL JUEGO Y PONER EL CONTADOR DE MOVIMIENTOS EN 0
        prepararJuego(); // SE ENCARGA DE ELEGIR E INSERTAR LOS NUMEROS EN LAS CARTAS ALEATORIAMENTE

    }
    fun onClick(v: View){
        valorClick = v.getId();
        if(juegoComenzo){
            buscandoPares(valorClick); // SE ENCARGA DE DESCUBRIR LAS CARTAS SI HAY PARIDAD DE NUMERO
        }else{
            Toast.makeText(this, "Apreta COMENZAR", Toast.LENGTH_SHORT).show()
        }
    }

      fun comenzarReiniciarJuego(btn : Button){
          if(btn.text.equals("Comenzar")){  // MODIFICA EL TEXTO DEL BOTON SEGUN CORRESPONDA
              btn.setText("Reiniciar");
              sumarMovimientos = 0;
              contadorMov.setText(sumarMovimientos.toString());
          }else{
              btn.setText("Comenzar");
              sumarMovimientos = 0
              contadorMov.setText(sumarMovimientos.toString());
          }
          juegoComenzo = true;
      }
      fun prepararJuego(){
      val cartas = mutableListOf(R.id.tarjeta1, R.id.tarjeta2, R.id.tarjeta3, R.id.tarjeta4,
          R.id.tarjeta5, R.id.tarjeta6, R.id.tarjeta7, R.id.tarjeta8,
          R.id.tarjeta9,R.id.tarjeta10 ,R.id.tarjeta11 ,R.id.tarjeta12,
          R.id.tarjeta13 ,R.id.tarjeta14 ,R.id.tarjeta15 ,R.id.tarjeta16); // LISTA MUTABLE CON LOS ID`S DE LAS CARTAS
      for(i in cartas){
          var carta = findViewById<Button>(i);
          carta.setBackgroundColor(Color.parseColor("#000000"))
      }
      for (numero in 1..8){ // ESTRUCTURA PARA ELEGIR NUMERO Y POSICION DENTRO DEL TABLERO
          for(y in 1..2){
              var cartaAleatoria = cartas.random(); // SELECCIONA CARTA ALEATORIA
              cartas.remove(cartaAleatoria); // SACA DE LA LISTA LA CARTA SELECCIONADA
              var carta = findViewById<Button>(cartaAleatoria); // SELECCIONA A TRAVES DEL ID LA CARTA
              carta.setText(numero.toString()); // INSERTA EL NUMERO EN LA CARTA
          }
      }
    }
      fun bloquearBotones(ok : Boolean, uno : Int, dos : Int){
          val cartas = mutableListOf(R.id.tarjeta1, R.id.tarjeta2, R.id.tarjeta3, R.id.tarjeta4,
              R.id.tarjeta5, R.id.tarjeta6, R.id.tarjeta7, R.id.tarjeta8,
              R.id.tarjeta9,R.id.tarjeta10 ,R.id.tarjeta11 ,R.id.tarjeta12,
              R.id.tarjeta13 ,R.id.tarjeta14 ,R.id.tarjeta15 ,R.id.tarjeta16);
          cartas.remove(uno);
          cartas.remove(dos);
          for(id in cartas){
              var button = findViewById<Button>(id);
              button.setEnabled(ok);
              Log.d("boton bloqueado",button.isEnabled().toString())
          }
      }
      fun buscandoPares(valorClick : Int){
        if(validadorClick){
            seleccionUno =  findViewById(valorClick);
            seleccionUno.setBackgroundColor(Color.parseColor("#FFFFFF"));
            validadorClick = false;
        }else if(valorClick != seleccionUno.getId() && seleccionUno.length() != 0){ // VALIDA QUE EL CLICK NO SEA EN UNA MISMA CARTA
            seleccionDos =  findViewById(valorClick);
            seleccionDos.setBackgroundColor(Color.parseColor("#FFFFFF"));
            validadorClick = true;
            bloquearBotones(false,seleccionUno.getId(),seleccionDos.getId());
            if(!seleccionUno.getText().equals(seleccionDos.getText()) ){ // VALIDA QUE NO SEAN NUMEROS IGUALES Y LOS OCULTA
                Handler().postDelayed(1000){
                    seleccionUno.setBackgroundColor(Color.parseColor("#000000"));
                    seleccionDos.setBackgroundColor(Color.parseColor("#000000"));
                }
            }
            Handler().postDelayed(1000){
                bloquearBotones(true,seleccionUno.getId(),seleccionDos.getId())
            }
            sumarMovimientos ++
            contadorMov.setText(sumarMovimientos.toString());
        }
    }

  }



