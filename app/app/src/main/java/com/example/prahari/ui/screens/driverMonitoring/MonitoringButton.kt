package com.example.prahari.ui.screens.driverMonitoring

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MonitoringButton(

    isMonitoring: Boolean,

    onClick: () -> Unit

) {

    val buttonColor =
        if (isMonitoring)
            Color(0xFFD32F2F)
        else
            Color(0xFF2E7D32)

    val icon =
        if (isMonitoring)
            Icons.Default.Close
        else
            Icons.Default.PlayArrow

    val text =
        if (isMonitoring)
            "Stop Monitoring"
        else
            "Start Monitoring"

    Button(

        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),

        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor
        ),

        onClick = onClick

    ) {

        Icon(
            imageVector = icon,
            contentDescription = null
        )

        Text(
            text = "  $text",
            style = MaterialTheme.typography.titleMedium
        )

    }

}