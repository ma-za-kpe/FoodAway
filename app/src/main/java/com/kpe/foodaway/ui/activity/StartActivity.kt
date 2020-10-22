package com.kpe.foodaway.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView
import com.kpe.foodaway.KitengeApplication
import com.kpe.foodaway.R
import com.kpe.foodaway.ui.fragment.StartFragment
import com.kpe.foodaway.ui.viewmodel.MainViewModel
import javax.inject.Inject

class StartActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)


        val navController: NavController = Navigation.findNavController(this, R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.startFragment, R.id.loginFragment, R.id.signUpFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

}