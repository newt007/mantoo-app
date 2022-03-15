package com.elapp.mantuapp.utils

import android.content.Context
import com.elapp.mantuapp.utils.ConstVal.IS_LOGIN_KEY
import com.elapp.mantuapp.utils.ConstVal.MANTOO_SHARED_PREFERENCES

class SessionManager(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences(MANTOO_SHARED_PREFERENCES, Context.MODE_PRIVATE)

    val getIsLogin = sharedPreferences.getBoolean(IS_LOGIN_KEY, false)

    fun updateIsLogin(login: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(IS_LOGIN_KEY, login)
        editor.apply()
    }

}