package com.kpe.foodaway.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kpe.foodaway.KitengeApplication
import com.kpe.foodaway.R
import com.kpe.foodaway.ui.base.BaseFragment
import com.kpe.foodaway.framework.model.local.ClientVendor
import com.kpe.foodaway.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_path.*
import kotlinx.coroutines.launch


class PathFragment : BaseFragment() {

    val prefManager = KitengeApplication.application.preferenceManager
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_path, container, false)
    }

//    override fun onResume() {
//        super.onResume()
//        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        vendor.setOnClickListener {
            launch {
//                viewModel.insert(ClientVendor("vendor","not-both"))
            }
//            findNavController().navigate(R.id.action_pathFragment_to_startFragment)
        }

        client.setOnClickListener {
            launch {
//                viewModel.insert(ClientVendor("client","not-both"))
            }
//            findNavController().navigate(R.id.action_pathFragment_to_mainActivity)
        }

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                PathFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}