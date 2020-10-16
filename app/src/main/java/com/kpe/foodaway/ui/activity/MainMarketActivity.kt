package com.kpe.foodaway.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.kpe.foodaway.R

class MainMarketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_main_settings -> {
                showSettingsScreen()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showSettingsScreen() {
        startActivity(SettingsActivity.getStartIntent(this))
    }

}