package com.example.instagram

import androidx.multidex.MultiDexApplication
import com.example.instagram.util.setLocalApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()
        setLocalApp("fa")
    }
}