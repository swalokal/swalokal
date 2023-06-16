package com.capstone.swalokal.ui.ProductImage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.swalokal.data.api.SwalokalRepository
import kotlinx.coroutines.launch
import com.capstone.swalokal.data.api.Result
import com.capstone.swalokal.data.api.response.PredictItem
import java.io.File

class ProductImageViewModel(private val swalokalRepository: SwalokalRepository) : ViewModel() {

    // upload and make prediction
    private val _uploadResult = MutableLiveData<Result<List<PredictItem>>>()
    val uploadResult: LiveData<Result<List<PredictItem>>> get() = _uploadResult

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun makePredictions(photo: File) {
        viewModelScope.launch {
            _loading.value = true
            val result = swalokalRepository.makePredictions(photo)
            _uploadResult.value = result
            _loading.value = false
        }
        Log.d("VM", uploadResult.toString())
    }

    // Actual Response Data
    fun makePredictionActualResponse(photo: File): LiveData<Result<List<PredictItem>>> {
        val resultLiveData = MutableLiveData<Result<List<PredictItem>>>()

        swalokalRepository.makePredictionActualResponse(photo) { result ->
            resultLiveData.postValue(result)
        }
        return resultLiveData
    }
}