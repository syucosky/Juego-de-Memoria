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


  class MainActivity : AppCompatActivity() {
      private lateinit var contadorMov: TextView; // UTILIZADO PARA CONTAR JUGADAS
      private var sumarMovimientos =
          0; // SUMA LOS MOVIMIENTOS PARA LUEGO INSERTARLOS EN EL contadorMov
      private var valorClick = 0; // UTILIZADO PARA PASAR EL ID DE LOS BOTONES CLICKEADOS
      private lateinit var seleccionUno: Button; // UTILIZADO PARA GUARDAR VALOR DEL PRIMER CLICK
      private lateinit var seleccionDos: Button; // UTILIZADO PARA GUARDAR VALOR DEL SEGUNDO CLICK
      private var validadorClick = true; // VALIDAR QUE SE HAYA CLICKEADO 1ra O 2do VEZ
      private var juegoComenzo = false;  // VALIDA ESTADO DEL JUEGO
      private var cartasDescubiertas: MutableList<Int> = mutableListOf();
      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_main);
          contadorMov = findViewById(R.id.txtContador);
          comenzarJuego(findViewById(R.id.btnReiniciarComenzar));
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
                  var carta =
                      findViewById<Button>(cartaAleatoria); // SELECCIONA A TRAVES DEL ID LA CARTA
                  carta.setText(numero.toString()); // INSERTA EL NUMERO EN LA CARTA
                  carta.setBackgroundColor(Color.parseColor("#000000"));
                  carta.setEnabled(true);
              }

          }
      }

      fun bloquearBotones(ok: Boolean, uno: Int, dos: Int) {
          var cartas = mutableListOf(
              R.id.tarjeta1, R.id.tarjeta2, R.id.tarjeta3, R.id.tarjeta4,
              R.id.tarjeta5, R.id.tarjeta6, R.id.tarjeta7, R.id.tarjeta8,
              R.id.tarjeta9, R.id.tarjeta10, R.id.tarjeta11, R.id.tarjeta12,
              R.id.tarjeta13, R.id.tarjeta14, R.id.tarjeta15, R.id.tarjeta16
          );
          cartas.remove(uno); // REMUEVE DE LA LISTA EL PRIMER BOTON CLICKEADO
          cartas.remove(dos); // REMUEVE DE LA LISTA EL SEGUNDO BOTON CLICKEADO
          for (id in cartas) {
              var button = findViewById<Button>(id);
              button.setEnabled(ok); // BLOQUEA LOS BOTONES
          }
      }

//      fun bloquearCartasDescubiertas(uno: Int, dos: Int) {
//          cartasDescubiertas.add(uno);
//          cartasDescubiertas.add(dos);
//          var button1 = findViewById<Button>(uno)
//          var button2 = findViewById<Button>(dos)
//          button1.setEnabled(false);
//          button2.setEnabled(false);
//      }
      fun addCartasAlista(uno : Int, dos: Int){
            cartasDescubiertas.add(uno);
            cartasDescubiertas.add(dos);
      }
      fun buscandoPares(valorClick: Int) {
          if(validadorClick && !cartasDescubiertas.contains(valorClick)){
              seleccionUno = findViewById(valorClick);
              seleccionUno.setBackgroundColor(Color.parseColor("#FFFFFF"))
              validadorClick = false;
          }else if(!validadorClick.toString().equals(seleccionUno) && !!cartasDescubiertas.contains(valorClick)){
              seleccionDos = findViewById(valorClick);
              seleccionDos.setBackgroundColor(Color.parseColor("#FFFFFF"));
              validadorClick = true;
          }
          if(validadorClick){
              bloquearBotones(false,seleccionUno.getId(),seleccionDos.getId());
              if(seleccionUno.getText().equals(seleccionDos.getText())){
                  seleccionUno.isEnabled = false;
                  seleccionDos.isEnabled = false;
                  addCartasAlista(seleccionUno.getId(),seleccionDos.getId());
              }else{
                  Handler().postDelayed(1000) {
                      seleccionUno.setBackgroundColor(Color.parseColor("#000000"));
                      seleccionDos.setBackgroundColor(Color.parseColor("#000000"));
                  }
              }
              Handler().postDelayed(500) {
                  bloquearBotones(true,seleccionUno.getId(),seleccionDos.getId());
              }
              sumarMovimientos++;
              contadorMov.setText(sumarMovimientos.toString());
          }
//          if (validadorClick && !cartasDescubiertas.contains(valorClick)) {
//              seleccionUno = findViewById(valorClick);
//              seleccionUno.setBackgroundColor(Color.parseColor("#FFFFFF")); // CAMBIA EL FONDO DEJANDO VER EL NUMERO DE LA CARTA
//              validadorClick = false; // HACE QUE EL SEGUNDO CLICK SEA TOMADO POR EL ELSE Y GUARDADO EN LA VARIABLE seleccionDos
//          } else if (valorClick != seleccionUno.getId() && seleccionUno.length() != 0 && !cartasDescubiertas.contains(valorClick)) { // VALIDA QUE EL CLICK NO SEA EN UNA MISMA CARTA
//              seleccionDos = findViewById(valorClick);
//              seleccionDos.setBackgroundColor(Color.parseColor("#FFFFFF"));
//              validadorClick =
//                  true; // ABRE LA ENTRADA AL IF PARA GUARDAR PRIMER CLICK EN seleccionUno
//              bloquearBotones(
//                  false,
//                  seleccionUno.getId(),
//                  seleccionDos.getId()
//              ); // BLOQUEA LOS BOTONES QUE NO HAYAN SIDO CLICKEADOS
//              if (!seleccionUno.getText().equals(seleccionDos.getText())) {
//                  if (!cartasDescubiertas.contains(seleccionUno.getId())) { // VALIDA QUE NO SEAN NUMEROS IGUALES Y LOS OCULTA
//                      Handler().postDelayed(875) {
//                          seleccionUno.setBackgroundColor(Color.parseColor("#000000"));
//                      }
//                  }
//                  if (!cartasDescubiertas.contains(seleccionDos.getId())) {
//                      Handler().postDelayed(875) {
//                          seleccionDos.setBackgroundColor(Color.parseColor("#000000"));
//                      }
//                  }
//              } else {
//                  bloquearCartasDescubiertas(seleccionUno.getId(), seleccionDos.getId());
//
//              }
//              Handler().postDelayed(875) {
//                  bloquearBotones(true, seleccionUno.getId(), seleccionDos.getId())
//              }
//              sumarMovimientos++
//              contadorMov.setText(sumarMovimientos.toString()); // SUMA EL MOVIMIENTO AL CONTADOR
//
//          }

      }
  }


