package com.example.instagram.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import com.example.instagram.BuildConfig
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

fun Context.hasNetwork(): Boolean {

    val connectManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){

        connectManager.activeNetwork ?.let { network ->
            connectManager.getNetworkCapabilities(network) ?.let { capabilities ->
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } ?: false
        } ?: false
    } else {
        val networkInfo : NetworkInfo ?= connectManager.activeNetworkInfo
        networkInfo?.run { isConnected == true } ?: false

    }
}
