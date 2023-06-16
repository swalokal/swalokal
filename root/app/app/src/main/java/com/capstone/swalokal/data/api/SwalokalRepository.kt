package com.capstone.swalokal.data.api

import android.util.Log
import com.capstone.swalokal.data.api.response.PredictItem
import com.capstone.swalokal.data.api.retrofit.ApiService
import com.capstone.swalokal.data.getActualResponseJsonResponse
import com.capstone.swalokal.parseResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class SwalokalRepository private constructor(private val apiService: ApiService) {

    // make prediction
    suspend fun makePredictions(photo: File): Result<List<PredictItem>> {
        val requestPhotoFile = photo.asRequestBody("image/jpg".toMediaType())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "photo",
            photo.name,
            requestPhotoFile
        )

        return try {
            val response = apiService.uploadPhoto(imageMultipart)
            if (response.isSuccessful) {
                val predictionResponse = response.body()
                predictionResponse?.let {
                    val result = processPredictionResponse(it)
                    if (result is Result.Success) {
                        Log.d("Repo", result.data.toString())
                        Result.Success(result.data)
                    } else {
                        Log.d("Repo", "Error 1")
                        Result.Error("Failed to get predictions")
                    }
                } ?: Result.Error("Empty response")
            } else {
                Log.d("Repo", "Error 2 : ${response.message()}")
                Result.Error(response.message())
            }
        } catch (e: Exception) {
            Log.d("Repo", "Error Exception")
            Result.Error(e.message ?: "Failed to upload photo")
        }
    }


    // Actual Response Data
    fun makePredictionActualResponse(photo: File, callback: (Result<List<PredictItem>>) -> Unit) {
        val actualResponseData = parseResponse(getActualResponseJsonResponse())
        callback(Result.Success(actualResponseData.data))
    }

    companion object {
        @Volatile
        private var instance: SwalokalRepository? = null
        fun getInstance(
            apiService: ApiService,
        ): SwalokalRepository =
            instance ?: synchronized(this) {
                instance ?: SwalokalRepository(apiService)
            }.also { instance = it }
    }
}