package com.kpe.foodaway

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.kpe.foodaway.util.PreferenceManager
import com.kpe.foodaway.util.ReleaseLogTree
import timber.log.Timber

class FoodStuff: Application() {
    companion object {
        // Singleton instance
        // Getter to access Singleton instance
        var instance: FoodStuff? = null
            private set

        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }


    val preferenceManager: PreferenceManager
        get() {
            return PreferenceManager.getInstance(this)!!
        }

    override fun onCreate() {
        super.onCreate()

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
}