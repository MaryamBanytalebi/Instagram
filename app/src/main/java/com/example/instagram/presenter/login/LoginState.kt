package com.example.instagram.presenter.login

import com.example.instagram.presenter.base.UiState

sealed class LoginState : UiState{

    object IDLE : LoginState()
    object ChangeLocal : LoginState()
    data class GetLocal(val languageId : String) : LoginState()


}
