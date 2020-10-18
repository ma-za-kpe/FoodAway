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
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            if (prefManager.isFirstTimeLaunch) {
                gotoWalkThrough()
            } else {
                if (prefManager.isClient){
                    goToMainActivity()
                } else if (prefManager.isVendor){
                    goToVendorActivity()
                }

            }
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }

    private fun goToVendorActivity() {
        this.launchActivity<VendorActivity>()
    }

    private fun gotoWalkThrough() {
        this.launchActivity<WalkThrough>()
    }

    private fun goToMainActivity() {
        this.launchActivity<MainMarketActivity>()
    }

}