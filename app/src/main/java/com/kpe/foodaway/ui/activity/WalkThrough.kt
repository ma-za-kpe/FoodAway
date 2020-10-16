package com.kpe.foodaway.ui.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment
import com.github.appintro.AppIntroPageTransformerType
import com.kpe.foodaway.R
import com.kpe.foodaway.base.BaseActivity

class WalkThrough : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        uiAway()
    }

    private fun skip() {
        goToMainActivity()
    }

    private fun goToMainActivity() {
        TODO("Not yet implemented")
    }


    private fun uiAway() {
        setTransformer(AppIntroPageTransformerType.Zoom)

        // Call addSlide passing your Fragments.
        // You can use AppIntroFragment to use a pre-built fragment
        addSlide(
            AppIntroFragment.newInstance(
                title = "All your favorites",
                description = "Browse & Order All Products \n" +
                        "at Any Time",
                imageDrawable = R.drawable.logo,
                titleColor = Color.WHITE,
                backgroundColor = resources.getColor(R.color.purple_700)

            )
        )
        addSlide(
            AppIntroFragment.newInstance(
                title = "Free delivery offers",
                description = "\n" +
                        "Browse & Order All Products \n" +
                        "at Any Time",
                imageDrawable = R.drawable.delivery,
                titleColor = Color.WHITE,
                backgroundColor = resources.getColor(R.color.purple_700)
            )
        )

        addSlide(
            AppIntroFragment.newInstance(
                title = "Safe Delivery",
                description = "You Package in\n" +
                        "Our Safe Hands",
                imageDrawable = R.drawable.safehands,
                titleColor = Color.WHITE,
                backgroundColor = resources.getColor(R.color.purple_700)
            )
        )

        addSlide(
            AppIntroFragment.newInstance(
                title = "On Time Delivery ",
                description = "Committed Delivery on Time",
                imageDrawable = R.drawable.ontime,
                titleColor = Color.WHITE,
                backgroundColor = resources.getColor(R.color.purple_700)
            )
        )

        addSlide(
            AppIntroFragment.newInstance(
                title = "Speedy & Timely ",
                description = "24/7\n" +
                        "    Fastest & Safest Delivery",
                imageDrawable = R.drawable.twentyfourseven,
                titleColor = Color.WHITE,
                backgroundColor = resources.getColor(R.color.purple_700)
            )
        )

        addSlide(
            AppIntroFragment.newInstance(
                title = "Realtime Tracking",
                description = "Track Your Delivery in\n" +
                        "Real Time",
                imageDrawable = R.drawable.realtime,
                titleColor = Color.WHITE,
                backgroundColor = resources.getColor(R.color.purple_700)
            )
        )

    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        // Decide what to do when the user clicks on "Skip"
//        prefManager.isFirstTimeLaunch = false
        goToActivity()
    }

    private fun goToActivity() {
        val myIntent = Intent(this, StartActivity::class.java)
        overridePendingTransition(0, 0)
        this.startActivity(myIntent)
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        // Decide what to do when the user clicks on "Done"
//        prefManager.isFirstTimeLaunch = false
        goToActivity()
    }

}