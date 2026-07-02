package com.example.prahari.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class DriverMonitoringUiState(

    val isMonitoring: Boolean = false,

    val driverStatus: String = "Awake",

    val faceDetected: Boolean = false,

    val eyesOpen: Boolean = false,

    val yawning: Boolean = false,

    val fps: Int = 0,

    val latency: Int = 0,

    val inferenceTime: Int = 0
)

class DriverMonitoringViewModel : ViewModel() {

    private val _uiState =
        MutableStateFlow(DriverMonitoringUiState())

    val uiState: StateFlow<DriverMonitoringUiState> =
        _uiState.asStateFlow()

    fun toggleMonitoring() {

        _uiState.value = _uiState.value.copy(
            isMonitoring = !_uiState.value.isMonitoring
        )

    }

}