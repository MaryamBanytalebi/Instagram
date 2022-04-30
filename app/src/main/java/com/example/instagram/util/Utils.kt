package com.example.instagram.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import java.util.*

fun Context.setLocalApp(languageCode: String) {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)
    val config = resources.configuration
    config.setLocale(locale)
    config.setLayoutDirection(locale)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        createConfigurationContext(config)

    resources.updateConfiguration(config, resources.displayMetrics)
}

fun Context.restartApp(activity: Activity) {
    val intent = Intent(this, activity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
    activity.finish()
    Runtime.getRuntime().exit(0)
}
