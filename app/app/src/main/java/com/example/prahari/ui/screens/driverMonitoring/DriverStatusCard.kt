package com.example.prahari.ui.screens.driverMonitoring

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DriverStatusCard(
    driverStatus: String
) {

    val statusColor = when (driverStatus) {
        "Awake" -> Color(0xFF4CAF50)
        "Drowsy" -> Color(0xFFFFC107)
        "Sleeping" -> Color(0xFFF44336)
        else -> Color.Gray
    }

    val statusMessage = when (driverStatus) {
        "Awake" -> "Everything looks normal."
        "Drowsy" -> "Stay alert. Fatigue detected."
        "Sleeping" -> "Wake up immediately!"
        else -> "No face detected."
    }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        tonalElevation = 3.dp
    ) {

        Row(
            modifier = Modifier
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(18.dp)
                    .background(
                        color = statusColor,
                        shape = CircleShape
                    )
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {

                Text(
                    text = "Driver Status",
                    style = MaterialTheme.typography.labelLarge
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = driverStatus,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = statusColor
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = statusMessage,
                    style = MaterialTheme.typography.bodyMedium
                )

            }

        }

    }

}