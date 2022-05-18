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
import com.example.instagram.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel.firstTime) viewModel.setEvent(LoginEvent.GetLocale)

        handleState()
        setupLanguageSpinner()
        handleEffect()
        onLoginClicked()
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
                    is LoginState.IDLE -> {
                    }
                    is LoginState.GetLocal ->
                        binding.spLanguages.setSelection(if (state.languageId == "en") 0 else 1)
                    is LoginState.Login.Loading -> renderLogin(true)
                    is LoginState.Login.Success -> viewModel.setEffect(LoginEffect.Login)
                    is LoginState.Login.Error -> renderLogin(errorMessage = state.message)
                }
            }
        }
    }

    private fun handleEffect() {
        lifecycleScope.launchWhenCreated {
            viewModel.effect.consumeAsFlow().collect() { effect ->
                when (effect) {
                    is LoginEffect.ChangeLocal -> {
                        requireContext().restartApp(requireActivity())
                    }
                    is LoginEffect.SignUp -> {
                        viewModel.firstTime = true
                        findNavController().navigate(R.id.signupFragment)
                    }

                    is LoginEffect.Login -> {}
                }
            }
        }
    }

    private fun onLoginClicked(){
        binding.btnLogin.setOnClickListener {
            viewModel.setEvent(LoginEvent.Login("", ""))
        }
    }

    private fun renderLogin(isLoading : Boolean = false, errorMessage : String = ""){
        with(binding){
            if (isLoading){
                loginProgress.visible()
                btnLogin.setEmpty()
            }else{
                loginProgress.invisible()
                btnLogin.text = getString(R.string.login)
                showToast(errorMessage)
            }
        }
    }
}
