package com.example.instagram.data.service

import com.google.gson.annotations.Expose

data class ApiResponse<T> (
    val success : Boolean,
    val status : String,
    @Expose val error : String,
    @Expose val data : T
    )

