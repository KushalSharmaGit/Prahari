package com.example.prahari.ml

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import java.nio.ByteBuffer
import java.nio.ByteOrder

class ImageConverter {

    companion object {
        private const val INPUT_SIZE = 80
        private const val CHANNELS = 3
        private const val FLOAT_SIZE = 4
    }

    private val inputBuffer = ByteBuffer.allocateDirect(
        INPUT_SIZE * INPUT_SIZE * CHANNELS * FLOAT_SIZE
    ).apply {
        order(ByteOrder.nativeOrder())
    }

    private val resizedBitmap =
        Bitmap.createBitmap(
            INPUT_SIZE,
            INPUT_SIZE,
            Bitmap.Config.ARGB_8888
        )

    private val canvas = Canvas(resizedBitmap)

    private val srcRect = Rect()
    private val dstRect = Rect(
        0,
        0,
        INPUT_SIZE,
        INPUT_SIZE
    )

    private val pixels =
        IntArray(INPUT_SIZE * INPUT_SIZE)

    fun convert(bitmap: Bitmap): ByteBuffer {

        srcRect.set(
            0,
            0,
            bitmap.width,
            bitmap.height
        )

        canvas.drawBitmap(
            bitmap,
            srcRect,
            dstRect,
            null
        )

        resizedBitmap.getPixels(
            pixels,
            0,
            INPUT_SIZE,
            0,
            0,
            INPUT_SIZE,
            INPUT_SIZE
        )

        inputBuffer.rewind()

        for (pixel in pixels) {

            inputBuffer.putFloat(((pixel shr 16) and 0xFF) / 255f)
            inputBuffer.putFloat(((pixel shr 8) and 0xFF) / 255f)
            inputBuffer.putFloat((pixel and 0xFF) / 255f)
        }

        inputBuffer.rewind()

        return inputBuffer
    }
}