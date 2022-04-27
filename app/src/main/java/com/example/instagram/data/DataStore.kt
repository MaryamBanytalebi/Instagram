package com.example.instagram.data

import android.content.Context
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.instagram.util.SETTING_LOCAL_NAME
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("setting")

class DataStore(context: Context) {

    private val dataStore = context.dataStore

    suspend fun getLocalApp() : String = dataStore.data.map { preferences ->
        preferences[stringPreferencesKey(SETTING_LOCAL_NAME)]
    }.first() ?: "en"

    suspend fun setLocalApp(languageCode : String){
        dataStore.edit { setting ->
            setting[stringPreferencesKey(SETTING_LOCAL_NAME)] = languageCode
        }
    }
}