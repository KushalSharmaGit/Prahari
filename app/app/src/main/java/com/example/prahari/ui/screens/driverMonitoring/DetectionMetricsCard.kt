package com.example.prahari.ui.screens.driverMonitoring

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DetectionMetricsCard(

    faceDetected: Boolean,

    eyesOpen: Boolean,

    yawning: Boolean,

    confidence: Int = 98

) {

    Surface(

        modifier = Modifier.fillMaxWidth(),

        tonalElevation = 3.dp,

        shape = MaterialTheme.shapes.large

    ) {

        Column(

            modifier = Modifier.padding(20.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {

            Text(

                text = "Detection Metrics",

                style = MaterialTheme.typography.titleMedium,

                fontWeight = FontWeight.Bold

            )

            MetricRow(
                title = "Face Detected",
                value = faceDetected
            )

            HorizontalDivider()

            MetricRow(
                title = "Eyes Open",
                value = eyesOpen
            )

            HorizontalDivider()

            MetricRow(
                title = "Yawning",
                value = yawning
            )

            HorizontalDivider()

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceBetween

            ) {

                Text(

                    text = "Confidence",

                    style = MaterialTheme.typography.bodyLarge

                )

                Text(

                    text = "$confidence%",

                    style = MaterialTheme.typography.bodyLarge,

                    fontWeight = FontWeight.Bold,

                    color = Color(0xFF4CAF50)

                )

            }

        }

    }

}

@Composable
private fun MetricRow(

    title: String,

    value: Boolean

) {

    Row(

        modifier = Modifier.fillMaxWidth(),

        verticalAlignment = Alignment.CenterVertically,

        horizontalArrangement = Arrangement.SpaceBetween

    ) {

        Text(

            text = title,

            style = MaterialTheme.typography.bodyLarge

        )

        if (value) {

            Icon(

                imageVector = Icons.Default.CheckCircle,

                contentDescription = null,

                tint = Color(0xFF4CAF50)

            )

        } else {

            Icon(

                imageVector = Icons.Default.Clear,

                contentDescription = null,

                tint = Color(0xFFE53935)

            )

        }

    }

}