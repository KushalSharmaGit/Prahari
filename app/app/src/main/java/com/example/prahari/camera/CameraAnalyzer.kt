package com.example.prahari.camera

import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy

class CameraAnalyzer(
    private val frameProcessor: FrameProcessor
) : ImageAnalysis.Analyzer {

    override fun analyze(image: ImageProxy) {

        Log.d("PRAHARI_CAMERA", "Frame Received")

        frameProcessor.process(image)
    }
}