package com.kpe.foodaway.ui.fragment

import android.R.attr.name
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.kpe.foodaway.R
import com.kpe.foodaway.base.BaseFragment
import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm
import kotlinx.android.synthetic.main.fragment_stepper.*
import timber.log.Timber


class StepperFragment : BaseFragment(), VerticalStepperForm {

    private lateinit var tax: EditText
    private lateinit var address: EditText
    private lateinit var license: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stepper, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mySteps = arrayOf("tax_info", "physical_address", "lisence_to_sell")
        val colorPrimary = ContextCompat.getColor(
            requireContext(),
            R.color.purple_700
        )
        val colorPrimaryDark = ContextCompat.getColor(
            requireContext(),
            R.color.purple_500
        )

        // Setting up and initializing the form
        VerticalStepperFormLayout.Builder.newInstance(
            vertical_stepper_form,
            mySteps,
            this,
            requireActivity()
        )
            .primaryColor(colorPrimary)
            .primaryDarkColor(colorPrimaryDark)
            .displayBottomNavigation(true) // It is true by default, so in this case this line is not necessary
            .init()
    }

    override fun createStepContentView(stepNumber: Int): View {
        var view: View? = null
        when (stepNumber) {
            0 -> view = createTaxInfoStep()
            1 -> view = createPhysicalAddressStep()
            2 -> view = createLinsenceToSellStep()
        }
        return view!!
    }

    private fun createTaxInfoStep(): View? {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        tax.isSingleLine = true
        tax.hint = "Your tax info"
        return tax;
    }

    private fun createPhysicalAddressStep(): View? {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        address.isSingleLine = true
        address.hint = "Your address info"
        return address;
    }

    private fun createLinsenceToSellStep(): View? {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        license.isSingleLine = true
        license.hint = "Your licence"
        return license
    }

    override fun onStepOpening(stepNumber: Int) {
        when (stepNumber) {
            0 -> checkTaxInfo()
            1 -> checkLinsenceToSell()
            2 -> checkPhysicalAddress()
            3 ->            // As soon as the Linsence step is open, we mark it as completed in order to show the "Continue"
                // button (We do it because this field is optional, so the user can skip it without giving any info)
                vertical_stepper_form.setStepAsCompleted(2)
        }
    }

    private fun checkPhysicalAddress() {
        if (tax.length() in 3..40) {
            vertical_stepper_form.setActiveStepAsCompleted()
        } else {
            // This error message is optional (use null if you don't want to display an error message)
            val errorMessage = "Missing PhysicalAddress info, The name must have between 3 and 40 characters"
            val dialog = showDialogue(errorMessage)
            errorDialogue(alertDialog = dialog)
            vertical_stepper_form.setActiveStepAsUncompleted(errorMessage)
        }
    }

    private fun checkLinsenceToSell() {
        if (tax.length() in 3..40) {
            vertical_stepper_form.setActiveStepAsCompleted()
        } else {
            // This error message is optional (use null if you don't want to display an error message)
            val errorMessage = "Missing Licence info, The name must have between 3 and 40 characters"
            val dialog = showDialogue(errorMessage)
            errorDialogue(alertDialog = dialog)
            vertical_stepper_form.setActiveStepAsUncompleted(errorMessage)
        }
    }

    private fun checkTaxInfo() {
        if (tax.length() in 3..40) {
            vertical_stepper_form.setActiveStepAsCompleted()
        } else {
            // This error message is optional (use null if you don't want to display an error message)
            val errorMessage = "Missing tax info, The name must have between 3 and 40 characters"
            val dialog = showDialogue(errorMessage)
            errorDialogue(alertDialog = dialog)
            vertical_stepper_form.setActiveStepAsUncompleted(errorMessage)
        }
    }

    override fun sendData() {
        Timber.d("data collected tax " + tax.text)
        Timber.d("data collected address " + address.text)
        Timber.d("data collected license " + license.text)
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StepperFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

}