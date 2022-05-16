package com.example.instagram.presenter.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<US : UiState, UE : UiEvent, UEF : UiEffect> : ViewModel() {

    private val initializeState: US by lazy { createInitialState() }
    protected abstract fun createInitialState(): US

    protected val _state = MutableStateFlow<US> (initializeState)
    protected val _event = Channel<UE> ()
    protected val _effect = Channel<UEF>()
    val effect get() = _effect
    val state : StateFlow<US> get() = _state

    init {
        handleEvents()
    }

    abstract fun handleEvents()

    fun setEvent(event: UE) {
        viewModelScope.launch {
            _event.send(event)
        }
    }

    fun setEffect(effect: UEF) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }
}