package com.example.juegodememoria

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class DialogoNuevoTop : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val input = EditText(context)
        return AlertDialog.Builder(context)
            .setMessage(getString(R.string.nuevoRecord))
            .setView(input)
            .setPositiveButton(getString(R.string.aceptar)) { dialog,which ->

                Toast.makeText(getActivity(), input.text, Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton(getString(R.string.cancelarSalir)) { dialog, which ->
                Toast.makeText(getActivity(),"El usuario decidió quedarse", Toast.LENGTH_SHORT).show();
            }
            .create()
    }
}