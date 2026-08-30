package com.example.prahari.vision

import android.graphics.RectF

data class FaceLandmarkerResult(
    val faceDetected: Boolean,
    val boundingBox: RectF?,
    val leftEye: List<Pair<Float, Float>>,
    val rightEye: List<Pair<Float, Float>>
)