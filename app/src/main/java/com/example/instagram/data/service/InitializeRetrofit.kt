package com.example.instagram.data.service

import android.content.Context
import com.example.instagram.BuildConfig
import com.example.instagram.R
import com.example.instagram.util.hasNetwork
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class InitializeRetrofit {

    fun retrofit(context : Context) = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/api/")
            .client(okHttp(context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    fun okHttp(context : Context): OkHttpClient {
        return if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(40, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(networkInterceptor(context))
                .build()
        } else {
            OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(networkInterceptor(context))
                .writeTimeout(40, TimeUnit.SECONDS)
                .build()
        }
    }

    private fun networkInterceptor(context : Context) : Interceptor = Interceptor {
        val request = it.request()
        try {
            if (context.hasNetwork()) {
                it.proceed(request)
            }else{
                throw NoConnectivityException(context.getString(R.string.err_no_internet))
            }
        }catch (e : SocketTimeoutException){
            throw SocketTimeoutException(context.getString(R.string.err_time_out))
        }catch (e : Exception){
            throw java.lang.Exception(context.getString(R.string.err_request_failed))
        }
    }

    class NoConnectivityException(message: String) : IOException(message)
}