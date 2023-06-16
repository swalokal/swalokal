package com.capstone.swalokal.ui.ProductImage

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.capstone.swalokal.ViewModelFactory
import com.capstone.swalokal.databinding.ActivityProductImageBinding
import com.capstone.swalokal.reduceFileImage
import com.capstone.swalokal.rotateFile
import com.capstone.swalokal.ui.Maps.MapsActivity
import java.io.File
import com.capstone.swalokal.data.api.Result
import com.example.storyapp.di.Injection

class ProductImageActivity : AppCompatActivity() {
    private var _binding: ActivityProductImageBinding? = null
    private val binding get() = _binding
    private var getFile: File? = null
    private lateinit var productImageViewModel: ProductImageViewModel

    companion object {
        private const val TAG = "ProductImageActivity"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onPause() {
        super.onPause()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProductImageBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        hideSystemUI()
        setupViewModel()

        val myFile = intent?.getSerializableExtra("picture") as? File
        val isBackCamera = intent?.getBooleanExtra("isBackCamera", true) as Boolean
        Log.d(TAG, "isBackCamera : $isBackCamera")

        myFile?.let {
            rotateFile(it, isBackCamera)
            getFile = it
            binding?.previewImageView?.setImageBitmap(BitmapFactory.decodeFile(it.path))
        }

        // Action
        binding?.findButton?.setOnClickListener {
            uploadImage()
        }
    }

    private fun setupViewModel() {
        val repository = Injection.provideRepository(this)
        val viewModelFactory = ViewModelFactory(repository)
        productImageViewModel =
            ViewModelProvider(this, viewModelFactory)[ProductImageViewModel::class.java]
    }

    // upload and make predict
    private fun uploadImage() {
        if (getFile != null) {
            val file = reduceFileImage(getFile as File)

//            productImageViewModel.makePredictions(file)
//            productImageViewModel.uploadResult.observe(this) { result ->
//                when (result) {
//                    is Result.Loading -> {
//                        binding?.progressBar?.visibility = View.GONE
//                    }
//                    is Result.Success -> {
//                        val predictions = result.data
//                        Log.d("PIA Sc", predictions.toString())
//                    }
//                    is Result.Error -> {
//                        val errorMessage = result.err
//                        Log.d("PIA Er", errorMessage)
//                        Toast.makeText(this, "Cannot process image", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }

            // Actual Response Data
            productImageViewModel.makePredictionActualResponse(file).observe(this) { result ->
                when (result) {
                    is Result.Loading -> {
                        binding?.progressBar?.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding?.progressBar?.visibility = View.GONE
                        val predictItems = result.data
                        val intent = Intent(this, MapsActivity::class.java)
                        intent.putParcelableArrayListExtra("predictItems", ArrayList(predictItems))
                        startActivity(intent)

                    }
                    is Result.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(this, "There was a problem uploading images", Toast.LENGTH_SHORT).show()
                        val errorMessage = result.err
                        Log.d(TAG, errorMessage)
                    }
                }
            }

        } else {
            Toast.makeText(
                this@ProductImageActivity,
                "Image cannot be empty",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun hideSystemUI() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }
}