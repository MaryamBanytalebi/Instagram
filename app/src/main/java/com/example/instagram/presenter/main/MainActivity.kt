package com.example.instagram.presenter.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.instagram.R
import com.example.instagram.data.DataStore
import com.example.instagram.databinding.ActivityMainBinding
import com.example.instagram.util.setLocalApp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> ()
    private lateinit var dataStore : DataStore
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataStore = DataStore(this)
        viewModel.setEvent(MainEvent.GetLocal)
        handleState()
    }

    private fun handleState(){
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect{ mainState ->
                when(mainState) {

                    is MainState.IDLE -> {}
                    is MainState.ChangeLocal -> setLocalApp(mainState.languageId)
                    is MainState.SetContentView -> {
                        binding = ActivityMainBinding.inflate(layoutInflater)
                        setContentView(binding.root)

                    }
                }
            }
        }
    }

    private fun setupNavHost(){
        val navHost = binding.mainContainer as NavHostFragment
    }
}