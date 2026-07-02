package com.example.prahari.camera

import android.util.Log
import androidx.camera.core.ImageProxy

class FrameProcessor {

    fun process(image: ImageProxy) {

        Log.d(
            "PRAHARI_FRAME",
            "Width: ${image.width}, Height: ${image.height}, Rotation: ${image.imageInfo.rotationDegrees}"
        )

        // TensorFlow Lite will be called here later

        image.close()
    }
}