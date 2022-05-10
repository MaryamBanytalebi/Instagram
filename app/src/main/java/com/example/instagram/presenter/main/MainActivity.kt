package com.example.instagram.presenter.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.instagram.R
import com.example.instagram.data.DataStore
import com.example.instagram.databinding.ActivityMainBinding
import com.example.instagram.util.gone
import com.example.instagram.util.setLocalApp
import com.example.instagram.util.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> ()
    private lateinit var dataStore : DataStore
    private lateinit var binding : ActivityMainBinding
    private lateinit var controller : NavController

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
                        setupNavHost()
                    }
                }
            }
        }
    }

    private fun setupNavHost(){
        val navHost = binding.mainContainer as NavHostFragment
        controller = navHost.navController
        controller.addOnDestinationChangedListener{ controller,destination, argument ->
            when (destination.id) {
                R.id.loginFragment, R.id.signupFragment ->
                    binding.bottomNavigation.gone()
                else -> {
                    binding.bottomNavigation.visible()
                }
            }
        }
    }
}