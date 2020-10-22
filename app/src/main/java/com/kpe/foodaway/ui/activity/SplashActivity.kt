package com.kpe.foodaway.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.kpe.foodaway.KitengeApplication
import com.kpe.foodaway.R
import com.kpe.foodaway.util.launchActivity

class SplashActivity : AppCompatActivity() {

    val prefManager = KitengeApplication.application.preferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            if (prefManager.isFirstTimeLaunch){
                gotoWalkThrough()
            } else {
                if (prefManager.isClient){
                    goToMainActivity()
                } else if (prefManager.isVendor){
                    goToVendorActivity()
                    gotoWalkThrough()
                } else {
                    if (prefManager.isClient){
                        goToMainActivity()
                    } else if (prefManager.isVendor){
                        goToVendorActivity()
                    }

                }
            }
        }, 3000)

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