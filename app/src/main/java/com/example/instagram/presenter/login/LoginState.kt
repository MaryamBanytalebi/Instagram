package com.example.instagram.presenter.login

import com.example.instagram.presenter.base.UiState

sealed class LoginState : UiState{

    object IDLE : LoginState()
    data class GetLocal(val languageId : String) : LoginState()

    sealed class Login{
        object Loading : LoginState()
        object Success : LoginState()
        data class Error(val message : String) : LoginState()
    }


}
