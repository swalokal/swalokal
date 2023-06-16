package com.capstone.swalokal.data.api

import com.capstone.swalokal.data.api.response.PredictItem
import com.capstone.swalokal.data.api.response.PredictionResponse

sealed class Result<out R> private constructor() {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val err: String) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

fun processPredictionResponse(response: PredictionResponse): Result<List<PredictItem>> {
    return if (response.detail.eror) {
        Result.Error("Failed to get predictions")
    } else {
        val predictItems = response.detail.data
        if (predictItems.isEmpty()) {
            Result.Error("Empty prediction result")
        } else {
            Result.Success(predictItems)
        }
    }
}