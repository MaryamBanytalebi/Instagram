package com.example.instagram.presenter.login

import com.example.instagram.presenter.base.UiEvent

sealed class LoginEvent : UiEvent{
    data class ChangeLocal(val languageId : String) : LoginEvent()
    data class Login(val username : String , val password : String) : LoginEvent()
    object GetLocale : LoginEvent()
    object Signup : LoginEvent()
    object ForgetPassword : LoginEvent()


}
