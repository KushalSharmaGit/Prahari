package com.example.prahari.camera

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import java.util.concurrent.Executors

class CameraController {

    // Dedicated background thread for image analysis
    private val cameraExecutor = Executors.newSingleThreadExecutor()

    fun startCamera(
        context: Context,
        lifecycleOwner: LifecycleOwner,
        previewView: PreviewView
    ) {

        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

        cameraProviderFuture.addListener({

            val cameraProvider = cameraProviderFuture.get()

            // Preview Use Case
            val preview = Preview.Builder()
                .build()
                .also {
                    it.surfaceProvider = previewView.surfaceProvider
                }

            // Image Analysis Use Case
            val imageAnalysis = ImageAnalysis.Builder()
                .setBackpressureStrategy(
                    ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
                )
                .build()

            imageAnalysis.setAnalyzer(
                cameraExecutor,
                CameraAnalyzer(FrameProcessor())
            )

            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            try {

                // Unbind any previously bound use cases
                cameraProvider.unbindAll()

                // Bind Preview + ImageAnalysis
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    preview,
                    imageAnalysis
                )

            } catch (exception: Exception) {

                exception.printStackTrace()

            }

        }, context.mainExecutor)
    }

    /**
     * Call this when the screen is destroyed
     * to avoid thread leaks.
     */
    fun shutdown() {
        cameraExecutor.shutdown()
    }
}