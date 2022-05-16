package com.example.instagram.di

import com.example.instagram.data.repository.AuthenticationRepository
import com.example.instagram.data.service.auth.AuthenticationApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideAuthenticationApiService(retrofit: Retrofit) =
        AuthenticationRepository(retrofit.create(AuthenticationApiService :: class.java))

}