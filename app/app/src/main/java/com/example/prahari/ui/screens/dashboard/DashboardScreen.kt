package com.example.prahari.ui.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.prahari.navigation.Screen

data class DashboardItem(
    val title: String,
    val subtitle: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navController: NavController
) {

    val dashboardItems = listOf(

        DashboardItem(
            title = "Driver Monitoring",
            subtitle = "Start live AI driver monitoring",
            icon = Icons.Default.DateRange,
            route = Screen.DriverMonitoring.route
        ),

        DashboardItem(
            title = "Reports",
            subtitle = "View driving reports",
            icon = Icons.Default.DateRange,
            route = ""
        ),

        DashboardItem(
            title = "Emergency",
            subtitle = "Emergency contacts & SOS",
            icon = Icons.Default.Warning,
            route = ""
        ),

        DashboardItem(
            title = "Profile",
            subtitle = "Manage your profile",
            icon = Icons.Default.Person,
            route = ""
        )

    )

    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(

                title = {

                    Text("Prahari")

                }

            )

        }

    ) { innerPadding ->

        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),

            contentPadding = PaddingValues(20.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {

            item {

                Text(

                    text = "Welcome 👋",

                    style = MaterialTheme.typography.headlineMedium

                )

            }

            item {

                Text(

                    text = "Choose a feature to begin.",

                    style = MaterialTheme.typography.bodyLarge

                )

            }

            items(dashboardItems) { item ->

                Card(

                    modifier = Modifier.fillMaxWidth(),

                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),

                    onClick = {

                        if (item.route.isNotEmpty()) {

                            navController.navigate(item.route)

                        }

                    }

                ) {

                    ListItem(

                        headlineContent = {

                            Text(item.title)

                        },

                        supportingContent = {

                            Text(item.subtitle)

                        },

                        leadingContent = {

                            Icon(

                                imageVector = item.icon,

                                contentDescription = null

                            )

                        }

                    )

                }

            }

        }

    }

}