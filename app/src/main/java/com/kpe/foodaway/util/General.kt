package com.kpe.foodaway.util

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Build
import android.provider.ContactsContract
import android.provider.ContactsContract.QuickContact
import android.provider.Settings
import android.text.TextUtils


object ImplicitIntentsUtil {
    /**
     * Start an intent. If it is possible for this app to handle the intent, force this app's
     * activity to handle the intent. Sometimes it is impossible to know whether this app
     * can handle an intent while coding since the code is used inside both Dialer and Contacts.
     * This method is particularly useful in such circumstances.
     *
     * On a Nexus 5 with a small number of apps, this method consistently added 3-16ms of delay
     * in order to talk to the package manager.
     */
    fun startActivityInAppIfPossible(context: Context, intent: Intent) {
        val appIntent = getIntentInAppIfExists(context, intent)
        if (appIntent != null) {
            context.startActivity(appIntent)
        } else {
            context.startActivity(intent)
        }
    }

    /**
     * Start intent using an activity inside this app. This method is useful if you are certain
     * that the intent can be handled inside this app, and you care about shaving milliseconds.
     */
    fun startActivityInApp(context: Context, intent: Intent) {
        val intent = Intent(this, DisplayMessageActivity::class.java)
        val packageName: String = context.getPackageName()
        intent.setPackage(packageName)
        context.startActivity(intent)
    }

    /**
     * Start an intent normally. Assert that the intent can't be opened inside this app.
     */
    fun startActivityOutsideApp(context: Context, intent: Intent) {
        val isPlatformDebugBuild = Build.TYPE == "eng" || Build.TYPE == "userdebug"
        if (isPlatformDebugBuild) {
            if (getIntentInAppIfExists(context, intent) != null) {
                throw AssertionError(
                    "startActivityOutsideApp() was called for an intent" +
                            " that can be handled inside the app"
                )
            }
        }
        context.startActivity(intent)
    }

    /**
     * Returns an implicit intent for opening QuickContacts.
     */
    fun composeQuickContactIntent(
        contactLookupUri: Uri?,
        extraMode: Int
    ): Intent {
        val intent = Intent(QuickContact.ACTION_QUICK_CONTACT)
        intent.data = contactLookupUri
        intent.putExtra(QuickContact.EXTRA_MODE, extraMode)
        // Make sure not to show QuickContacts on top of another QuickContacts.
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        return intent
    }

    /**
     * When adding account
     * open the same UI screen for user to choose account
     */
    val intentForAddingAccount: Intent
        get() {
            val intent = Intent(Settings.ACTION_ADD_ACCOUNT)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET
            intent.putExtra(Settings.EXTRA_AUTHORITIES, arrayOf(ContactsContract.AUTHORITY))
            return intent
        }

    /**
     * Returns a copy of {@param intent} with a class name set, if a class inside this app
     * has a corresponding intent filter.
     */
    private fun getIntentInAppIfExists(context: Context, intent: Intent): Intent? {
        return try {
            val intentCopy = Intent(intent)
            // Force this intentCopy to open inside the current app.
            intentCopy.setPackage(context.getPackageName())
            val list: List<ResolveInfo> = context.getPackageManager().queryIntentActivities(
                intentCopy, PackageManager.MATCH_DEFAULT_ONLY
            )
            if (list != null && list.size != 0) {
                // Now that we know the intentCopy will work inside the current app, we
                // can return this intent non-null.
                if (list[0].activityInfo != null
                    && !TextUtils.isEmpty(list[0].activityInfo.name)
                ) {
                    // Now that we know the class name, we may as well attach it to intentCopy
                    // to prevent the package manager from needing to find it again inside
                    // startActivity(). This is only needed for efficiency.
                    intentCopy.setClassName(
                        context.getPackageName(),
                        list[0].activityInfo.name
                    )
                }
                return intentCopy
            }
            null
        } catch (e: Exception) {
            // Don't let the package manager crash our app. If the package manager can't resolve the
            // intent here, then we can still call startActivity without calling setClass() first.
            null
        }
    }
}