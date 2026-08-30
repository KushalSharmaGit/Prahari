package com.example.prahari.camera

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.example.prahari.ml.ModelRunner
import java.util.concurrent.Executors

class CameraController {

    private val cameraExecutor =
        Executors.newSingleThreadExecutor()

    private var modelRunner: ModelRunner? = null

    fun startCamera(
        context: Context,
        lifecycleOwner: LifecycleOwner,
        previewView: PreviewView
    ) {

        modelRunner = ModelRunner(context)

        val cameraProviderFuture =
            ProcessCameraProvider.getInstance(context)

        cameraProviderFuture.addListener({

            val cameraProvider =
                cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.surfaceProvider =
                        previewView.surfaceProvider
                }

            val imageAnalysis =
                ImageAnalysis.Builder()
                    .setBackpressureStrategy(
                        ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
                    )
                    .build()

            imageAnalysis.setAnalyzer(

                cameraExecutor,

                CameraAnalyzer(

                    FrameProcessor(
                        context,
                        modelRunner!!
                    )

                )

            )

            val cameraSelector =
                CameraSelector.DEFAULT_FRONT_CAMERA

            try {

                cameraProvider.unbindAll()

                cameraProvider.bindToLifecycle(

                    lifecycleOwner,

                    cameraSelector,

                    preview,

                    imageAnalysis

                )

            } catch (e: Exception) {

                e.printStackTrace()

            }

        }, context.mainExecutor)
    }

    fun shutdown() {

        modelRunner?.close()

        cameraExecutor.shutdown()

    }
}