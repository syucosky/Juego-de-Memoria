package com.example.juegodememoria

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONArray
import org.json.JSONException

class Ranking(private val context: Context) {
    private val PREFS_NAME = "Rank5"
    private val KEY_TOP5 = "top5"

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
}