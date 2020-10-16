package com.kpe.foodaway.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson


class PreferenceManager(val context: Context) {

    private val prefName: String? = null

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

    /**
     * Puts given key-value pair to the Shared Preferences.
     *
     * @param key
     * @param value - String
     */
    fun put(key: String?, value: String?) {
        val edit = context.getSharedPreferences(prefName, Context.MODE_PRIVATE).edit()
        edit.putString(key, value).commit()
    }

    /**
     * Puts given key-value pair to the Shared Preferences.
     *
     * @param key
     * @param value - boolean
     */
    fun put(key: String?, value: Boolean) {
        val edit = context.getSharedPreferences(prefName, Context.MODE_PRIVATE).edit()
        edit.putBoolean(key, value).commit()
    }

    /**
     * Puts given key-value pair to the Shared Preferences.
     *
     * @param key
     * @param value - long
     */
    fun put(key: String?, value: Long) {
        val edit = context.getSharedPreferences(prefName, Context.MODE_PRIVATE).edit()
        edit.putLong(key, value).commit()
    }

    /**
     * Puts given key-value pair to the Shared Preferences.
     *
     * @param key
     * @param value - float
     */
    fun put(key: String?, value: Float) {
        val edit = context.getSharedPreferences(prefName, Context.MODE_PRIVATE).edit()
        edit.putFloat(key, value).commit()
    }

    /**
     * Puts given key-value pair to the Shared Preferences.
     *
     * @param key
     * @param value - int
     */
    fun put(key: String?, value: Int) {
        val edit = context.getSharedPreferences(prefName, Context.MODE_PRIVATE).edit()
        edit.putInt(key, value).commit()
    }

    /**
     * Returns String value for the given key, null if no value is found.
     *
     * @param key
     * @return String
     */
    fun getString(key: String?): String? {
        return context?.getSharedPreferences(prefName, Context.MODE_PRIVATE)?.getString(key, null)
    }

//    /**
//     * Contains preference name constants. These must be unique.
//     */
//    object Pref {
//        val allPreferenceFileNames: Array<String>
//            get() {
//                val preferencesFilesList: Array<String> = INSTANCE.pref.all
//                for (i in preferencesFilesList.indices) {
//                    preferencesFilesList[i] += ".xml"
//                }
//                return preferencesFilesList
//            }
//    }
//
//    /**
//     * Clears all the shared preferences that are used in the app.
//     *
//     * @param exceptions Names of the preferences that need to be skipped while clearing.
//     */
//    fun nukeSharedPreferences(exceptions: List<String?>) {
//        for (prefName in Pref.getAll()) {
//            if (exceptions.contains(prefName)) {
//                continue
//            }
//            context.getSharedPreferences(
//                    prefName, Context.MODE_PRIVATE).edit().clear().apply()
//        }
//    }

}

