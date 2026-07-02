package com.example.prahari.camera

import android.annotation.SuppressLint
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("UnsafeOptInUsageError")
@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    previewView: PreviewView
) {

    AndroidView(
        modifier = modifier,
        factory = {
            previewView
        }
    )

}