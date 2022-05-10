package com.example.instagram.di

import android.content.Context
import com.example.instagram.data.DataStore
import com.example.instagram.data.service.InitializeRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent :: class)
object AppModule {

    @Provides
    @Singleton
    fun providesDataStore(@ApplicationContext context: Context) = DataStore(context)
    @Provides
    @Singleton
    fun provideRetrofit() = InitializeRetrofit().retrofit()
    @Provides
    fun provideApiService(retrofit: Retrofit) = InitializeRetrofit().apiService(retrofit)
}