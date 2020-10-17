package com.kpe.foodaway.ui.fragment

import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.kpe.foodaway.FoodStuff
import com.kpe.foodaway.R
import kotlinx.android.synthetic.main.fragment_forgot.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.email_address
import timber.log.Timber

class LoginFragment : Fragment() {

    val prefManager = FoodStuff.instance!!.preferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
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

        if (prefManager.isClient){
            //change color if vendor
            (activity as AppCompatActivity?)!!.supportActionBar!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.purple_500)))
            login.setBackgroundColor(resources.getColor(R.color.purple_500))
            log_in_fb.strokeColor = ColorStateList.valueOf(resources.getColor(R.color.purple_500))
            log_in_google.strokeColor = ColorStateList.valueOf(resources.getColor(R.color.purple_500))
            bluebackground.setBackgroundColor(resources.getColor(R.color.purple_500))
        } else if (prefManager.isVendor){
            //change color if vendor
            (activity as AppCompatActivity?)!!.supportActionBar!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.purple_700)))
            login.setBackgroundColor(resources.getColor(R.color.purple_700))
            log_in_fb.strokeColor = ColorStateList.valueOf(resources.getColor(R.color.purple_700))
            log_in_google.strokeColor = ColorStateList.valueOf(resources.getColor(R.color.purple_700))
            bluebackground.setBackgroundColor(resources.getColor(R.color.purple_700))
        }

        //hightlihging
        //build the spannable String for 50 shillings
        val optional = resources.getString(R.string.login_text);

        val spannableO = SpannableString(optional);
        spannableO.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.purple_700)),
            22, 30,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        login_texts_orsingup.text = spannableO

        //go to signup
        login_texts_orsingup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        ui()
    }

    private fun ui() {
        // Get input email text
        val inputemailText = email_address.editText?.text.toString()

        // Get input password text
        val inputpasswordText = password_toggle.editText?.text.toString()

//        filledTextField.editText?.doOnTextChanged { inputText, _, _, _ ->
//            // Respond to input text change
//        }
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