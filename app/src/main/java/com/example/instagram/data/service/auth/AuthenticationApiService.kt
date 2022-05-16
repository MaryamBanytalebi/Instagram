package com.example.instagram.data.service.auth

import com.example.instagram.data.service.ApiResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthenticationApiService {

    @POST("login")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username : String ?= null,
        @Field("password") password : String ?= null
    ): Response<ApiResponse<Boolean>>

    @POST("signup")
    @FormUrlEncoded
    suspend fun signup(
        @Field("phone_number") phoneNumber: String? = null,
        @Field("email") email: String? = null
    ): Response<ApiResponse<Boolean>>
}