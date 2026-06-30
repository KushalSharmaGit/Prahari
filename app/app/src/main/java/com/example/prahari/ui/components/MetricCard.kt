package com.example.prahari.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MetricCard(

    title: String,

    value: String

) {

    PrimaryCard {

        Text(

            text = title,

            style = MaterialTheme.typography.titleMedium

        )

        Text(

            text = value,

            style = MaterialTheme.typography.headlineMedium,

            color = MaterialTheme.colorScheme.primary

        )

    }

}