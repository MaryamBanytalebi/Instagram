package com.example.instagram.di

import android.content.Context
import com.example.instagram.data.DataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent :: class)
object AppModule {

    @Provides
    fun providesDataStore(@ApplicationContext context: Context) = DataStore(context)
}