package com.example.instagram.data.repository

sealed class RepositoryResult{

    //states repository return yo viewModel
object Loading : RepositoryResult()

data class Error(val message: String) : RepositoryResult()

data class Data<T>(val data: T) : RepositoryResult()

}
