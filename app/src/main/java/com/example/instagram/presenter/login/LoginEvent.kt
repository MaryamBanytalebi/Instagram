package com.example.instagram.presenter.login

import com.example.instagram.presenter.base.UiEvent

sealed class LoginEvent : UiEvent{
    data class ChangeLocal(val languageId : String) : LoginEvent()
    object GetLocale : LoginEvent()

}
