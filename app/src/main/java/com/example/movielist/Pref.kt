package com.example.movielist

import android.content.Context
import android.content.SharedPreferences

class Pref (context: Context)
{
    private val APP_PREF_INT_EXAMPLE = "favPref"

    private val preferences: SharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)

    var favPref: MutableSet<String>
        get() = preferences.getStringSet(APP_PREF_INT_EXAMPLE, mutableSetOf())!!
        set(value) = preferences.edit().putStringSet(APP_PREF_INT_EXAMPLE, value).apply()
}