package com.kpe.foodaway.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.kpe.foodaway.R
import com.kpe.foodaway.ui.activity.StartActivity
import com.kpe.foodaway.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class StartFragment : Fragment() {

    companion object {
        fun newInstance() = StartFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the Vie wModel

        login.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_loginFragment)
        }

        signup.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_signUpFragment)
        }
    }

}