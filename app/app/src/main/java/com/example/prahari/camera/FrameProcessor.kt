package com.example.prahari.camera

import android.util.Log
import androidx.camera.core.ImageProxy
import com.example.prahari.ml.ImageConverter
import com.example.prahari.ml.ImageUtils
import com.example.prahari.ml.ModelRunner
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
class FrameProcessor(
    private val context: Context,
    private val modelRunner: ModelRunner
){

    private val imageConverter = ImageConverter()

    private fun vibratePhone() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

            val vibratorManager =
                context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE)
                        as VibratorManager

            vibratorManager.defaultVibrator.vibrate(
                VibrationEffect.createOneShot(
                    500,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )

        } else {

            @Suppress("DEPRECATION")
            val vibrator =
                context.getSystemService(Context.VIBRATOR_SERVICE)
                        as Vibrator

            @Suppress("DEPRECATION")
            vibrator.vibrate(500)

        }
    }

    fun process(image: ImageProxy) {

        try {

            val bitmap =
                ImageUtils.imageProxyToBitmap(image)

            val inputBuffer =
                imageConverter.convert(bitmap)

            val result =
                modelRunner.run(inputBuffer)

            val status =
                if (result.isSleepy){

                    vibratePhone()

                    "Sleepy"

                } else {

                    "Awake"

                }

            Log.d(
                "PRAHARI_AI",
                """
                Status: ${if(result.isSleepy) "Sleepy" else "Awake"}
                Sleepy: ${result.sleepyProbability}
                Awake: ${result.awakeProbability}
                Confidence: ${result.confidence}
                """.trimIndent()
            )

        } catch (e: Exception) {

            Log.e(
                "PRAHARI_AI",
                "Inference Failed",
                e
            )

        } finally {

            image.close()

        }
    }
}