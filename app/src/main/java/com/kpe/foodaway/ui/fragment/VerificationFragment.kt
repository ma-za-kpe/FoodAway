package com.kpe.foodaway.ui.fragment

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kpe.foodaway.R
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_sign_up.signup_texts
import kotlinx.android.synthetic.main.fragment_verification.*

class VerificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_verification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val optional = resources.getString(R.string.didn_t_receive_code);
        val spannableO = SpannableString(optional);
        spannableO.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.purple_700)),
            21, 37,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        dindt_recieve.text = spannableO
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment VerificationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VerificationFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}