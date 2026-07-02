package com.example.prahari.ui.screens.driverMonitoring

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PerformanceCard(

    fps: Int,

    latency: Int,

    inferenceTime: Int

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

                text = "Performance",

                style = MaterialTheme.typography.titleMedium,

                fontWeight = FontWeight.Bold

            )

            PerformanceRow(

                title = "FPS",

                value = fps.toString()

            )

            HorizontalDivider()

            PerformanceRow(

                title = "Latency",

                value = "$latency ms"

            )

            HorizontalDivider()

            PerformanceRow(

                title = "Inference",

                value = "$inferenceTime ms"

            )

        }

    }

}

@Composable
private fun PerformanceRow(

    title: String,

    value: String

) {

    Row(

        modifier = Modifier.fillMaxWidth(),

        horizontalArrangement = Arrangement.SpaceBetween

    ) {

        Text(

            text = title,

            style = MaterialTheme.typography.bodyLarge

        )

        Text(

            text = value,

            style = MaterialTheme.typography.bodyLarge,

            fontWeight = FontWeight.Bold

        )

    }

}