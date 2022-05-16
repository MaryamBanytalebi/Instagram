package com.example.instagram.presenter.login

import androidx.lifecycle.viewModelScope
import com.example.instagram.data.DataStore
import com.example.instagram.data.repository.AuthenticationRepository
import com.example.instagram.data.repository.RepositoryResult
import com.example.instagram.presenter.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataStore: DataStore,
    private val authRepository: AuthenticationRepository
) : BaseViewModel<LoginState, LoginEvent, LoginEffect>() {

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
                        dataStore.setLocalApp(event.languageId)
                        _effect.send(LoginEffect.ChangeLocal)
                    }
                    is LoginEvent.Login -> {
                        login(event.username,event.password)
                    }
                }
            }
        }
    }

    private fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.login(username, password).collect { result ->
                when (result) {
                    is RepositoryResult.Loading -> _state.emit(LoginState.Login.Loading)
                    is RepositoryResult.Data<*> -> _state.emit(LoginState.Login.Success)
                    is RepositoryResult.Error -> _state.emit(LoginState.Login.Error(result.message))
                }
            }
        }
    }
}