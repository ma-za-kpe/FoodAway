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
                description = "Order from the best local restaurants with easy, on-demand delivery.",
                imageDrawable = R.drawable.one,
                titleColor = Color.WHITE,
                backgroundColor = resources.getColor(R.color.purple_700)

            )
        )
        addSlide(
            AppIntroFragment.newInstance(
                title = "Free delivery offers",
                description = "Free delivery for new customers via Apple Pay and others payment methods.",
                imageDrawable = R.drawable.two,
                titleColor = Color.WHITE,
                backgroundColor = resources.getColor(R.color.purple_700)
            )
        )

        addSlide(
            AppIntroFragment.newInstance(
                title = "Choose your food",
                description = "Easily find your type of food craving and you’ll get delivery in wide range.",
                imageDrawable = R.drawable.three,
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
        val myIntent = Intent(this, MainActivity::class.java)
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