package com.kpe.foodaway.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kpe.foodaway.FoodStuff
import com.kpe.foodaway.R

class SplashActivity : AppCompatActivity() {

    val prefManager = FoodStuff.instance!!.preferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (prefManager.isFirstTimeLaunch) {
            gotoWalkThrough()
        } else {
            goToMainActivity()
        }
    }

    private fun gotoWalkThrough() {
        val myIntent = Intent(this, WalkThrough::class.java)
        this.startActivity(myIntent)
    }

    private fun goToMainActivity() {
        val myIntent = Intent(this, MainActivity::class.java)
        this.startActivity(myIntent)
    }


}