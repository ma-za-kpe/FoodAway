package com.kpe.foodaway.ui.base

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import cn.pedant.SweetAlert.SweetAlertDialog
import com.kpe.foodaway.KitengeApplication
import timber.log.Timber

open class BaseActivity : AppCompatActivity() {

    var isConnected: Boolean = true

    private var alertDialog: SweetAlertDialog? = null

    val prefManager = KitengeApplication.application.preferenceManager

    //globally provides same view model instance
//    val vm by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        changeStatusBarColor("#FFFFFF")
    }

    override fun isFinishing(): Boolean {
        dismissDialogue(alertDialog)
        return super.isFinishing()
    }

    override fun onDestroy() {
        super.onDestroy()

        dismissDialogue(alertDialog)
    }

    fun close(v: View) {
        Timber.i("$v")
        onBackPressed()
    }

    fun hightlightText(
        optional: String,
        start: Int,
        end: Int,
        textview: TextView,
        color: Int
    ) {

        val spannableO = SpannableString(optional);
        spannableO.setSpan(
            ForegroundColorSpan(color),
            start, end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        textview.text = spannableO
    }

    //region Dialogues

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun showDialogue(
        title: String = "",
        descriptions: String = "Please wait...",
        cancelable: Boolean = false
    ): SweetAlertDialog? {
        try {
            if (!this.isFinishing) {
                alertDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                    .setTitleText(title)
                    .setContentText(descriptions)
                alertDialog?.setCancelable(cancelable)
                alertDialog?.show()
                return alertDialog
            }

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
        if (!this.isFinishing) {
            alertDialog?.changeAlertType(SweetAlertDialog.ERROR_TYPE)
            alertDialog?.titleText = title
            alertDialog?.contentText = descriptions
            Handler().postDelayed({ alertDialog?.dismiss() }, 5000)
            alertDialog?.setCancelable(true)
            alertDialog?.show()
        }
    }

    fun successDialogue(
        title: String = "Success",
        descriptions: String = "",
        alertDialog: SweetAlertDialog?
    ) {
        try {
            if (!this.isFinishing) {
                alertDialog?.changeAlertType(SweetAlertDialog.SUCCESS_TYPE)
                alertDialog?.titleText = title
                alertDialog?.contentText = descriptions
                alertDialog?.show()
                Handler().postDelayed({ alertDialog?.dismiss() }, 1000)
                alertDialog?.setCancelable(true)
            }


        } catch (e: WindowManager.BadTokenException) {
            Timber.e(e)
        }
    }

    override fun onResume() {
        super.onResume()
        isConnected = true
    }

    fun warningDialogue(
        title: String = "Warning",
        descriptions: String = "",
        alertDialog: SweetAlertDialog?
    ): SweetAlertDialog? {
        try {
            if (!this.isFinishing) {
                alertDialog?.changeAlertType(SweetAlertDialog.WARNING_TYPE)
                alertDialog?.titleText = title
                alertDialog?.contentText = descriptions
                alertDialog?.setCancelable(true)
                alertDialog?.show()
                return alertDialog
            }

        } catch (e: WindowManager.BadTokenException) {
            Timber.e(e)
        }
        return null
    }

    //endregion

    fun hideSystemNavOnly() {
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                )
    }

    fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                )
    }


    // Shows the system bars by removing all the flags
    // except for the ones that make the content appear under the system bars.
    fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }


    fun getMyColor(@ColorRes color: Int): Int {
        return ContextCompat.getColor(this, color)
    }

    fun getMyDrawable(@DrawableRes drawable: Int): Drawable {
        return ContextCompat.getDrawable(this, drawable)!!
    }

    fun makeFullScreen() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    fun changeStatusBarColor(color: String) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.parseColor(color)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

}
