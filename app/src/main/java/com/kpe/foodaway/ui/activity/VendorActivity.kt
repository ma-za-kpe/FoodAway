package com.kpe.foodaway.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kpe.foodaway.R
import com.kpe.foodaway.ui.fragment.VendorFragment

class VendorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vendor_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, VendorFragment.newInstance())
                    .commitNow()
        }
    }
}