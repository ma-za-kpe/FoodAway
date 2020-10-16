package com.kpe.foodaway

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.kpe.foodaway.util.PreferenceManager
import com.kpe.foodaway.util.ReleaseLogTree
import com.kpe.foodaway.util.ThemeManager
import timber.log.Timber

class FoodStuff: Application() {
    companion object {
        // Singleton instance
        // Getter to access Singleton instance
        var instance: FoodStuff? = null
            private set
    }


    val preferenceManager: PreferenceManager
        get() {
            return PreferenceManager.getInstance(this)!!
        }

    override fun onCreate() {
        super.onCreate()

        initTheme()

        // Setup singleton instance
        instance = this

        //plant timber
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                //Add the line number to the tag
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return super.createStackElementTag(element) + ": " + element.lineNumber
                }
            })
        } else {
            //Release mode
            Timber.plant(ReleaseLogTree())
        }
    }

    private fun initTheme() {
        val preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this)
        ThemeManager.applyTheme(preferences.getString("preference_key_theme", "")!!)
    }
}