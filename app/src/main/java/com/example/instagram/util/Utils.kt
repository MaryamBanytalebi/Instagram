package com.example.instagram.util

import android.content.Context
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

        resources.updateConfiguration(config,resources.displayMetrics)
    }
