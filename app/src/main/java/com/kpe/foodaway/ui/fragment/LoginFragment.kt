package com.kpe.foodaway.ui.fragment

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kpe.foodaway.R
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.main_fragment.*

class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //hightlihging
        //build the spannable String for 50 shillings
        val optional = resources.getString(R.string.login_text);

        val spannableO = SpannableString(optional);
        spannableO.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.purple_700)),
            49, 68,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        login_texts.text = spannableO

        //go to signup
        login_texts.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}