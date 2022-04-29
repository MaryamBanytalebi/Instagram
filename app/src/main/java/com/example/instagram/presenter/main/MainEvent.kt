package com.example.instagram.presenter.main

import com.example.instagram.presenter.base.UiEvent

sealed class MainEvent : UiEvent{
    object GetLocal : MainEvent()
    object SetContentView : MainEvent()

}