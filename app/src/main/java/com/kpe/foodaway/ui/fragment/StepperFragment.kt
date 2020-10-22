package com.kpe.foodaway.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.kpe.foodaway.R
import com.kpe.foodaway.ui.base.BaseFragment
import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm
import kotlinx.android.synthetic.main.address_step_layout.*
import kotlinx.android.synthetic.main.fragment_stepper.*
import kotlinx.android.synthetic.main.licence_step_layout.*
import kotlinx.android.synthetic.main.tax_step_layout.*
import timber.log.Timber


class StepperFragment : BaseFragment(), VerticalStepperForm {

//    private var tax: EditText? = null
//    private var address: EditText? = null
//    private var license: EditText? = null

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
        // In this case we generate the view by inflating a XML file

        // In this case we generate the view by inflating a XML file
        val inflater = LayoutInflater.from(requireContext())
        val taxLayoutContent =
            inflater.inflate(R.layout.tax_step_layout, null, false) as ConstraintLayout
        tax?.hint = "Your tax info"
        return taxLayoutContent
    }

    private fun createPhysicalAddressStep(): View? {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        // In this case we generate the view by inflating a XML file
        val inflater = LayoutInflater.from(requireContext())
        val addressLayoutContent =
            inflater.inflate(R.layout.address_step_layout, null, false) as ConstraintLayout
        address?.hint = "Your address info"
        return addressLayoutContent
    }

    private fun createLinsenceToSellStep(): View? {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        // In this case we generate the view by inflating a XML file
        val inflater = LayoutInflater.from(requireContext())
        val licenceLayoutContent =
            inflater.inflate(R.layout.licence_step_layout, null, false) as ConstraintLayout
        licence?.hint = "Your licence"
        return licenceLayoutContent
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
        if (address.editText?.text?.length in 3..40) {
            vertical_stepper_form.setActiveStepAsCompleted()
        } else {
            // This error message is optional (use null if you don't want to display an error message)
            val errorMessage = "Missing PhysicalAddress info, The name must have between 3 and 40 characters"
//            val dialog = showDialogue(errorMessage)
//            errorDialogue(alertDialog = dialog)
            vertical_stepper_form.setActiveStepAsUncompleted(errorMessage)
        }
    }

    private fun checkLinsenceToSell() {
        if (licence.editText?.text?.length == 0 ) {
            // This error message is optional (use null if you don't want to display an error message)
            val errorMessage = "Missing Licence info, The name must have between 3 and 40 characters"
//            val dialog = showDialogue(errorMessage)
//            errorDialogue(alertDialog = dialog)
            vertical_stepper_form.setActiveStepAsUncompleted(errorMessage)
        }

        vertical_stepper_form.setActiveStepAsCompleted()

    }

    private fun checkTaxInfo() {
        if (tax.editText?.text?.length in 3..40) {
            vertical_stepper_form.setActiveStepAsCompleted()
        } else {
            // This error message is optional (use null if you don't want to display an error message)
            val errorMessage = "Missing tax info, The name must have between 3 and 40 characters"
//            val dialog = showDialogue(errorMessage)
//            errorDialogue(alertDialog = dialog)
            vertical_stepper_form.setActiveStepAsUncompleted(errorMessage)
        }
    }

    override fun sendData() {
        Timber.d("data collected tax %s", tax.editText?.text)
        Timber.d("data collected address %s", address.editText?.text)
        Timber.d("data collected license %s", licence.editText?.text)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            StepperFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

}