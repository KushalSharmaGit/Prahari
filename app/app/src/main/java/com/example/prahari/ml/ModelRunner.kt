package com.example.prahari.ml

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.util.Log
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class ModelRunner(context: Context) {

    data class PredictionResult(
        val sleepyProbability: Float,
        val awakeProbability: Float,
        val isSleepy: Boolean,
        val confidence: Float
    )
    companion object {
        private const val TAG = "PRAHARI_MODEL"
        private const val MODEL_NAME = "prahari_lite.tflite"
    }

    private val interpreter: Interpreter

    init {

        val options = Interpreter.Options().apply {
            setNumThreads(4)
        }

        interpreter = Interpreter(loadModelFile(context), options)

        Log.d(TAG, "TensorFlow Lite Model Loaded")
    }

    private fun loadModelFile(context: Context): MappedByteBuffer {

        val fileDescriptor: AssetFileDescriptor =
            context.assets.openFd(MODEL_NAME)

        val inputStream =
            FileInputStream(fileDescriptor.fileDescriptor)

        val fileChannel =
            inputStream.channel

        return fileChannel.map(
            FileChannel.MapMode.READ_ONLY,
            fileDescriptor.startOffset,
            fileDescriptor.declaredLength
        )
    }

    fun run(inputBuffer: ByteBuffer): PredictionResult {

        val output = Array(1) { FloatArray(2) }

        interpreter.run(inputBuffer, output)

        /*
         * IMPORTANT
         *
         * During benchmarking we discovered that:
         *
         * Output[0] = Sleepy
         * Output[1] = Awake
         */

        val sleepy = output[0][0]
        val awake = output[0][1]

        val isSleepy = sleepy > awake

        val confidence =
            if (isSleepy) sleepy
            else awake

        return PredictionResult(
            sleepyProbability = sleepy,
            awakeProbability = awake,
            isSleepy = isSleepy,
            confidence = confidence
        )
    }

    fun close() {
        interpreter.close()
    }
}