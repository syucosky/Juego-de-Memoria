package com.example.juegodememoria

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONArray
import org.json.JSONException

class Ranking(private val context: Context) {
    private val PREFS_NAME = "MyPrefs"
    private val KEY_TOP5 = "top5"

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
    fun getNombre() : String{
        return "top5";
    }

    fun saveTop5Players(top5: JSONArray) {
        val editor = sharedPreferences.edit()
        editor.putString(KEY_TOP5, top5.toString())
        editor.apply()
    }

    fun getTop5Players(): JSONArray {
        val top5String = sharedPreferences.getString(KEY_TOP5, "")
        return try {
            JSONArray(top5String)
        } catch (e: JSONException) {
            JSONArray()
        }
    }
}