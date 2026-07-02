package com.example.prahari.ui.screens.driverMonitoring

import android.content.pm.PackageManager
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.prahari.camera.CameraController
import com.example.prahari.camera.CameraPreview
import com.example.prahari.camera.rememberCameraPermission
import com.example.prahari.viewmodel.DriverMonitoringViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverMonitoringScreen(
    viewModel: DriverMonitoringViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val previewView = remember {
        PreviewView(context)
    }

    val cameraController = remember {
        CameraController()
    }

    val (hasPermission, requestPermission) =
        rememberCameraPermission()
    LaunchedEffect(Unit) {

        if (!hasPermission) {

            requestPermission()

        }

    }
    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(
                title = {
                    Text("Driver Monitoring")
                }
            )

        }

    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {

                CameraPreviewCard {

                    if (hasPermission) {

                        LaunchedEffect(Unit) {
                            cameraController.startCamera(
                                context,
                                lifecycleOwner,
                                previewView
                            )
                        }

                        CameraPreview(
                            modifier = Modifier.fillMaxSize(),
                            previewView = previewView
                        )

                    } else {

                        Button(
                            onClick = requestPermission
                        ) {
                            Text("Grant Camera Permission")
                        }

                    }

                }

            }

            item {
                DriverStatusCard(uiState.driverStatus)
            }

            item {
                DetectionMetricsCard(
                    faceDetected = uiState.faceDetected,
                    eyesOpen = uiState.eyesOpen,
                    yawning = uiState.yawning
                )
            }

            item {
                PerformanceCard(
                    fps = uiState.fps,
                    latency = uiState.latency,
                    inferenceTime = uiState.inferenceTime
                )
            }

            item {
                MonitoringButton(
                    isMonitoring = uiState.isMonitoring,
                    onClick = {
                        viewModel.toggleMonitoring()
                    }
                )
            }

        }

    }

}