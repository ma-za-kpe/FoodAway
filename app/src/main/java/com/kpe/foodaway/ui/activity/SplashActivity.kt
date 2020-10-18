package com.kpe.foodaway.ui.activity

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.kpe.foodaway.FoodStuff
import com.kpe.foodaway.R
import com.kpe.foodaway.util.Constants.SPLASH_DISPLAY_LENGTH
import com.kpe.foodaway.util.launchActivity

class SplashActivity : AppCompatActivity() {

    val prefManager = FoodStuff.instance!!.preferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            if (prefManager.isFirstTimeLaunch) {
<<<<<<< HEAD
                gotoWalkThrough(this, intent)
            } else {
                if (prefManager.isClient){
                    goToMainActivity(this, intent)
                } else if (prefManager.isVendor){
                    goToVendorActivity(this, intent)
=======
                gotoWalkThrough()
            } else {
                if (prefManager.isClient){
                    goToMainActivity()
                } else if (prefManager.isVendor){
                    goToVendorActivity()
>>>>>>> 3aa30e2b630fe20cd6e15bbb4e4e4d30586dea81
                }

            }
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }

<<<<<<< HEAD
    private fun goToVendorActivity(splashActivity: SplashActivity, intent: Intent) {
        startActivityInApp(splashActivity, intent)
    }

    private fun gotoWalkThrough(splashActivity: SplashActivity, intent: Intent) {
        startActivityInApp(splashActivity, intent)
    }

    private fun goToMainActivity(splashActivity: SplashActivity, intent: Intent) {
        startActivityInApp(splashActivity, intent)
=======
    private fun goToVendorActivity() {
        this.launchActivity<VendorActivity>()
    }

    private fun gotoWalkThrough() {
        this.launchActivity<WalkThrough>()
    }

    private fun goToMainActivity() {
        this.launchActivity<MainMarketActivity>()
>>>>>>> 3aa30e2b630fe20cd6e15bbb4e4e4d30586dea81
    }

}