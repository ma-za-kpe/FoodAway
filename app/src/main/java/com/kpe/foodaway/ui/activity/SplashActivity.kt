package com.kpe.foodaway.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.kpe.foodaway.FoodStuff
import com.kpe.foodaway.R
import com.kpe.foodaway.util.Constants.SPLASH_DISPLAY_LENGTH
import com.kpe.foodaway.util.ImplicitIntentsUtil.startActivityInApp

class SplashActivity : AppCompatActivity() {

    val prefManager = FoodStuff.instance!!.preferenceManager
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            if (prefManager.isFirstTimeLaunch) {
                gotoWalkThrough(context, intent)
            } else {
                if (prefManager.isClient){
                    goToMainActivity(context, intent)
                } else if (prefManager.isVendor){
                    goToVendorActivity(context, intent)
                }

            }
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }

    private fun goToVendorActivity(context: Context, intent: Intent) {
        startActivityInApp(this.context, intent)
    }

    private fun gotoWalkThrough(context: Context, intent: Intent) {
        startActivityInApp(this.context, intent)
    }

    private fun goToMainActivity(context: Context, intent: Intent) {
        startActivityInApp(this.context, intent)
    }

}