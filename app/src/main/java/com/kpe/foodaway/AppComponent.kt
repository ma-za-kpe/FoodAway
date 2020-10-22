package com.kpe.foodaway

import com.kpe.foodaway.framework.network.KitengeApiModule
import com.kpe.foodaway.ui.activity.StartActivity
import com.kpe.foodaway.ui.fragment.LoginFragment
import com.kpe.foodaway.ui.fragment.StartFragment
import com.kpe.foodaway.ui.viewmodel.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, KitengeApiModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(loginFragment: LoginFragment)
}