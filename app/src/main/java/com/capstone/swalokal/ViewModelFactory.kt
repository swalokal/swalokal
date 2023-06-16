package com.capstone.swalokal


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.swalokal.data.api.SwalokalRepository
import com.capstone.swalokal.ui.ProductImage.ProductImageViewModel

class ViewModelFactory(private val repository: SwalokalRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {

            // repo
            modelClass.isAssignableFrom(ProductImageViewModel::class.java) -> {
                ProductImageViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }

}
