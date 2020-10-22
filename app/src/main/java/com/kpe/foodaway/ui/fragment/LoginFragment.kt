package com.kpe.foodaway.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kpe.foodaway.KitengeApplication
import com.kpe.foodaway.KitengeApplication.Companion.application
import com.kpe.foodaway.R
import com.kpe.foodaway.databinding.FragmentLoginBinding
import com.kpe.foodaway.ui.base.BaseFragment
import com.kpe.foodaway.ui.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    val prefManager = KitengeApplication.application.preferenceManager

    private lateinit var mViewBinding: FragmentLoginBinding
    private lateinit var viewModel: MainViewModel
    @Inject
     lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mViewBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_login, container, false)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        application.appComponent.inject(this)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(
            MainViewModel::class.java)

        mViewBinding.lifecycleOwner = this

        mViewBinding.mainviewModel = viewModel

        mViewBinding.loginLogin.setOnClickListener {
            loginUser()
        }

        // Inflate the layout for this fragment
        return mViewBinding.root
    }

    private fun loginUser() {
        launch {
            viewModel.login()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        if (prefManager.isClient){
//            toast("VENDORRR")
//            //change color if vendor
//            (activity as AppCompatActivity?)!!.supportActionBar!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.purple_500)))
//            login_login.setBackgroundColor(resources.getColor(R.color.purple_500))
//            log_in_fb.strokeColor = ColorStateList.valueOf(resources.getColor(R.color.purple_500))
//            log_in_google.strokeColor = ColorStateList.valueOf(resources.getColor(R.color.purple_500))
//            bluebackground.setBackgroundColor(resources.getColor(R.color.purple_500))
//        } else if (prefManager.isVendor){
//            toast("CLIENTTTT")
//            //change color if vendor
//            (activity as AppCompatActivity?)!!.supportActionBar!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.purple_700)))
//            login_login.setBackgroundColor(resources.getColor(R.color.purple_700))
//            log_in_fb.strokeColor = ColorStateList.valueOf(resources.getColor(R.color.purple_700))
//            log_in_google.strokeColor = ColorStateList.valueOf(resources.getColor(R.color.purple_700))
//            bluebackground.setBackgroundColor(resources.getColor(R.color.purple_700))
//        }


        //go to signup
        mViewBinding.loginTextsOrsingup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        //hightlihging
        val optional = resources.getString(R.string.login_text);
        hightlightText(optional, 22, 30,  mViewBinding.loginTextsOrsingup, resources.getColor(R.color.purple_700))
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}