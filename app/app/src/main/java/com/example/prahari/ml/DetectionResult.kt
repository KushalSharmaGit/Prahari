package com.example.prahari.ml

data class DetectionResult(

    val driverStatus: String,

    val confidence: Float,

    val eyesOpen: Boolean,

    val faceDetected: Boolean,

    val yawning: Boolean

)