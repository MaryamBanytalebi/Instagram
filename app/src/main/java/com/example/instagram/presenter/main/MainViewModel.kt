package com.example.instagram.presenter.main

import androidx.lifecycle.viewModelScope
import com.example.instagram.data.DataStore
import com.example.instagram.presenter.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dataStore: DataStore) :
    BaseViewModel<MainState, MainEvent, MainEffect>() {
    override fun createInitialState(): MainState  = MainState.IDLE

    override fun handleEvents() {

        viewModelScope.launch {
            _event.consumeAsFlow().collect { mainEvent ->
                when(mainEvent){
                    is MainEvent.GetLocal -> {
                        val local = dataStore.getLocalApp()
                        _state.emit(MainState.ChangeLocal(local))
                        _state.emit(MainState.SetContentView)
                    }
                }
            }
        }
   }
}