package com.kpe.foodaway.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class PreferenceManager(val context: Context) {

    companion object {
        private var INSTANCE: PreferenceManager? = null

        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): PreferenceManager? {
            if (INSTANCE == null) {
                INSTANCE = PreferenceManager(context)
            }
            return INSTANCE
        }
    }

    private val pref: SharedPreferences = context.getSharedPreferences(
        Constants.MyPREFERENCES,
        Context.MODE_PRIVATE
    )
    private var editor: SharedPreferences.Editor
    private val gson = Gson()

    init {
        editor = pref.edit()
    }

    var isFirstTimeLaunch: Boolean
        get() = pref.getBoolean(Constants.IS_FIRST_TIME_LAUNCH, true)
        set(isFirstTime) {
            editor.putBoolean(Constants.IS_FIRST_TIME_LAUNCH, isFirstTime)
            editor.apply()
        }

    fun <T : Any> storeItem(data: T) {
        val type = object : TypeToken<T>() {}.type
        val identity = data.javaClass.name
        val dataString = gson.toJson(data, type)
        editor.putString(identity, dataString).apply()
    }

    inline fun <reified T : Any> getItem(): T? {
        val type = object : TypeToken<T>() {}.type
        val identity = T::class.java.name

        val pref: SharedPreferences = context.getSharedPreferences(
            Constants.MyPREFERENCES,
            Context.MODE_PRIVATE
        )
        val dataString = pref.getString(identity, null)

        val gson = GsonBuilder().create()
        return gson.fromJson<T>(dataString, type)
    }
}

