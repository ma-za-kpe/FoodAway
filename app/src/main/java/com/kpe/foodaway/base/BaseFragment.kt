package com.kpe.foodaway.base

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import cn.pedant.SweetAlert.SweetAlertDialog
import com.rommansabbir.networkx.NetworkX
import timber.log.Timber

open class BaseFragment : Fragment() {

    var isConnected: Boolean = false



    private var alertDialog: SweetAlertDialog? = null

    //globally provides same view model instance
//    val vm by lazy { ViewModelProvider(requireActivity()).get(MainViewModel::class.java) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NetworkX.isConnectedLiveData().observe(viewLifecycleOwner, {
            isConnected = it
            Timber.d("Base net state=> $it")
            if (it) {
                //Do your stuff here when internet is connected

            } else {
                // Do your stuff here when internet is not connected
                //createSnack(requireActivity(), txt =  getString(R.string.no_net))
            }
        })
    }

    override fun onStop() {
        super.onStop()
        dismissDialogue(alertDialog)

    }

    override fun onDestroy() {
        super.onDestroy()

        dismissDialogue(alertDialog)
    }

    //region Dialogues

    fun showDialogue(
        title: String = "",
        descriptions: String = "Please wait...",
        cancelable: Boolean = false
    ): SweetAlertDialog? {
        try {
            alertDialog = SweetAlertDialog(requireContext(), SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText(title)
                .setContentText(descriptions)
            alertDialog?.setCancelable(cancelable)
            alertDialog?.show()
            return alertDialog


        } catch (e: WindowManager.BadTokenException) {
            Timber.e(e)
        }
        return null
    }

    fun dismissDialogue(alertDialog: SweetAlertDialog?) {
        alertDialog?.dismissWithAnimation()
    }

    fun errorDialogue(
        title: String = "Error",
        descriptions: String = "Please try again!",
        alertDialog: SweetAlertDialog?
    ) {
        alertDialog?.changeAlertType(SweetAlertDialog.ERROR_TYPE)
        alertDialog?.titleText = title
        alertDialog?.contentText = descriptions
        Handler().postDelayed({ alertDialog?.dismiss() }, 5000)
        alertDialog?.setCancelable(true)
        alertDialog?.show()

    }

    fun successDialogue(
        title: String = "Success",
        descriptions: String = "",
        alertDialog: SweetAlertDialog?
    ) {
        try {
            alertDialog?.changeAlertType(SweetAlertDialog.SUCCESS_TYPE)
            alertDialog?.titleText = title
            alertDialog?.contentText = descriptions
            alertDialog?.show()
            Handler().postDelayed({ alertDialog?.dismiss() }, 1000)
            alertDialog?.setCancelable(true)


        } catch (e: WindowManager.BadTokenException) {
            Timber.e(e)
        }
    }

    fun warningDialogue(
        title: String = "Warning",
        descriptions: String = "",
        alertDialog: SweetAlertDialog?
    ): SweetAlertDialog? {
        try {
            alertDialog?.changeAlertType(SweetAlertDialog.WARNING_TYPE)
            alertDialog?.titleText = title
            alertDialog?.contentText = descriptions
            alertDialog?.setCancelable(true)
            alertDialog?.show()
            return alertDialog


        } catch (e: WindowManager.BadTokenException) {
            Timber.e(e)
        }
        return null
    }

    //endregion


    fun getMyColor(@ColorRes color: Int): Int {
        return ContextCompat.getColor(requireContext(), color)
    }

    fun getMyDrawable(@DrawableRes drawable: Int): Drawable {
        return ContextCompat.getDrawable(requireContext(), drawable)!!
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun changeStatusBarColor(color: String) {
        val window = requireActivity().window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.parseColor(color)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
}

fun BaseFragment.toast(txt: String){
    Toast.makeText(this.requireContext(), txt, Toast.LENGTH_SHORT).show()
}
