package com.kpe.foodaway.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kpe.foodaway.R
import com.kpe.foodaway.ui.fragment.SettingsFragment

class SettingsActivity : AppCompatActivity() {

    companion object {
        fun getStartIntent(context: Context) = Intent(context, SettingsActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SettingsFragment.newInstance())
                .commitNow()
        }
    }
}