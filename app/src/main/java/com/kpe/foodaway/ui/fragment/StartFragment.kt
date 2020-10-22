package com.kpe.foodaway.ui.fragment

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kpe.foodaway.KitengeApplication
import com.kpe.foodaway.R
import com.kpe.foodaway.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject


class StartFragment : Fragment() {

    val prefManager = KitengeApplication.application.preferenceManager

    companion object {
        fun newInstance() = StartFragment()
    }

    private lateinit var viewModel: MainViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        KitengeApplication.application.appComponent.inject(this)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(
            MainViewModel::class.java)
        // TODO: Use the Vie wModel

        // change primary colors universally is vendor or client
        if (prefManager.isClient){
            (activity as AppCompatActivity?)!!.supportActionBar!!.setBackgroundDrawable(
                ColorDrawable(resources.getColor(R.color.purple_500)))
            login_start.setBackgroundColor(resources.getColor(R.color.purple_500))
            signup_start.setBackgroundColor(resources.getColor(R.color.purple_500))
        } else if (prefManager.isVendor){
            (activity as AppCompatActivity?)!!.supportActionBar!!.setBackgroundDrawable(
                ColorDrawable(resources.getColor(R.color.purple_700)))
            login_start.setBackgroundColor(resources.getColor(R.color.purple_700))
            signup_start.setBackgroundColor(resources.getColor(R.color.purple_700))
        }

        login_start.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_loginFragment)
        }

        signup_start.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_signUpFragment)
        }
    }

}