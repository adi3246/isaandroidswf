package com.airasia.swf.utils

import android.content.Context
import android.content.SharedPreferences
import com.airasia.swf.ApplicationClass

class PrefManager constructor(context: Context) {
    val TOKEN = "token"

    var pref: SharedPreferences = context.getSharedPreferences(context.packageName!!, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = pref.edit()

    var token: String
        get() = pref.getString(TOKEN, "").toString()
        set(value) {
            editor.putString(TOKEN, value)
            editor.apply()
        }

    companion object {
        val instance: PrefManager by lazy {
            PrefManager(ApplicationClass.contextApp)
        }
    }
}