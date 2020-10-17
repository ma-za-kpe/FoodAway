package com.kpe.foodaway.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kpe.foodaway.FoodStuff
import com.kpe.foodaway.R
import kotlinx.android.synthetic.main.fragment_path.*


class PathFragment : Fragment() {

    val prefManager = FoodStuff.instance!!.preferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_path, container, false)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vendor.setOnClickListener {
            prefManager.isVendor = true
            findNavController().navigate(R.id.action_pathFragment_to_startFragment)
        }

        client.setOnClickListener {
            prefManager.isClient = true
            findNavController().navigate(R.id.action_pathFragment_to_mainActivity)
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