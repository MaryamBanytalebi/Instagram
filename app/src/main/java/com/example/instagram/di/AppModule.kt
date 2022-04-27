package com.example.instagram.di

import android.content.Context
import com.example.instagram.data.DataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent :: class)
object AppModule {

    @Provides
    fun providesDataStore(context: Context) = DataStore(context)
}