package com.example.instagram.presenter.login

import com.example.instagram.presenter.base.UiEffect

sealed class LoginEffect : UiEffect {
    object ChangeLocal : LoginEffect()
    object SignUp : LoginEffect()
    object Login : LoginEffect()
    object ForgetPassword : LoginEffect()
}
