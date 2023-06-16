package com.example.storyapp.di

import android.content.Context
import com.capstone.swalokal.data.api.SwalokalRepository
import com.capstone.swalokal.data.api.retrofit.ApiConfig


object Injection {
    fun provideRepository(context: Context) : SwalokalRepository {
        val apiService = ApiConfig.getApiService()
        return SwalokalRepository.getInstance(apiService)
    }

}