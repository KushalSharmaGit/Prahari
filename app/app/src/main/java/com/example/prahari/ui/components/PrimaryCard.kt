package com.example.prahari.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryCard(

    modifier: Modifier = Modifier,

    content: @Composable () -> Unit

) {

    Card(

        modifier = modifier,

        shape = MaterialTheme.shapes.large,

        colors = CardDefaults.cardColors(

            containerColor = MaterialTheme.colorScheme.surface

        ),

        elevation = CardDefaults.cardElevation(

            defaultElevation = 6.dp

        )

    ) {

        Column(

            modifier = Modifier.padding(20.dp)

        ) {

            content()

        }

    }

}