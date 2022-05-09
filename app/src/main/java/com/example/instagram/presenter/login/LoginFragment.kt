package com.example.instagram.presenter.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.instagram.R
import com.example.instagram.databinding.FragmentLoginBinding
import com.example.instagram.presenter.base.BaseFragment
import com.example.instagram.presenter.main.MainState
import com.example.instagram.util.restartApp
import com.example.instagram.util.setLocalApp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setEvent(LoginEvent.GetLocale)
        handleState()
        setupLanguageSpinner()
    }

    private fun setupLanguageSpinner() {

        binding.spLanguages.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                if (viewModel.firstTime) {
                    viewModel.firstTime = false
                } else {
                    viewModel.setEvent(
                        LoginEvent.ChangeLocal(if (position == 0) "en" else "fa")
                    )
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun handleState() {

        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                when (state) {
                    is LoginState.IDLE -> { }
                    is LoginState.GetLocal ->
                        binding.spLanguages.setSelection(if (state.languageId == "en")0 else 1)

                }
            }
        }
    }

    private fun handleEffect(){
        lifecycleScope.launchWhenCreated {
            viewModel.effect.consumeAsFlow().collect(){ effect ->
                when (effect){
                    is LoginEffect.ChangeLocal -> {

                    }
                    is LoginEffect.SignUp -> {

                    }
                }

            }
        }
    }
}
