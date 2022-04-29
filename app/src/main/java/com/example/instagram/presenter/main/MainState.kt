package com.example.instagram.presenter.main

import com.example.instagram.presenter.base.UiState

sealed class MainState : UiState {

    object IDLE : MainState()
    object SetContentView : MainState()
    data class ChangeLocal(val languageId: String) : MainState()
}