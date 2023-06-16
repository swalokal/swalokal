package com.capstone.swalokal

import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.result.contract.ActivityResultContracts
import com.capstone.swalokal.databinding.ActivityMainBinding
import com.capstone.swalokal.ui.Camera.CameraActivity
import com.capstone.swalokal.ui.ProductImage.ProductImageActivity
import java.io.File

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding ?= null
    private val binding get() = _binding
    private var getFile: File? = null

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
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        hideSystemUI()

        binding?.galleryOption?.setOnClickListener { startGallery() }
        binding?.cameraOption?.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a picture")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectemImg = result?.data?.data as Uri
            selectemImg.let { uri ->
                val myFile = uriToFile(uri, this@MainActivity)
                getFile = myFile

                val intent = Intent(this, ProductImageActivity::class.java)
                intent.putExtra("picture", getFile)
                startActivity(intent)

            }
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