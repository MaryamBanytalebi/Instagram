package com.example.instagram.presenter.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.instagram.R
import com.example.instagram.data.DataStore
import com.example.instagram.databinding.ActivityMainBinding
import com.example.instagram.util.setLocalApp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var dataStore : DataStore
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataStore = DataStore(this)
        runBlocking {
            setLocalApp(dataStore.getLocalApp())
        }
        setContentView(R.layout.activity_main)
    }

    private fun setupNavHost(){
        val navHost = binding.mainContainer as NavHostFragment
    }
}