package com.example.instagram.presenter.login

import androidx.lifecycle.viewModelScope
import com.example.instagram.data.DataStore
import com.example.instagram.presenter.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataStore: DataStore
) : BaseViewModel<LoginState, LoginEvent>() {

    var firstTime = true
    override fun createInitialState() = LoginState.IDLE

    override fun handleEvents() {

        viewModelScope.launch {

            _event.consumeAsFlow().collect { event ->
                when (event) {
                    is LoginEvent.GetLocale -> {
                        val languageId = dataStore.getLocalApp()
                        _state.emit(LoginState.GetLocal(languageId))
                    }
                    is LoginEvent.ChangeLocal -> {
                        _state.emit(LoginState.ChangeLocal)
                    }
                }
            }
        }
    }
}