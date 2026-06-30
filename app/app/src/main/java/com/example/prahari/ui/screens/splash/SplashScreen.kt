package com.example.prahari.ui.screens.splash



import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SplashScreen(
    navController: NavController,
) {

    val infiniteTransition = rememberInfiniteTransition(label = "")

    // Logo Pulse Animation
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.7f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1A1A1A),
            Color.Black
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = Modifier
                    .size(96.dp)
                    .scale(scale)
                    .alpha(alpha)
                    .background(
                        Color(0x3300E5B0),
                        CircleShape
                    )
                    .border(
                        2.dp,
                        Color(0xFF00E5B0),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "P",
                    color = Color(0xFF00E5B0),
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Bold
                )

            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Prahari",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Your AI-Powered Driver Safety Assistant",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(56.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                BounceDot(delay = 0)

                BounceDot(delay = 150)

                BounceDot(delay = 300)

            }

        }

    }

}

@Composable
fun BounceDot(
    delay: Int
) {

    val transition = rememberInfiniteTransition(label = "")

    val offset by transition.animateFloat(
        initialValue = 0f,
        targetValue = -10f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 450,
                delayMillis = delay,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    Box(
        modifier = Modifier
            .offset(y = offset.dp)
            .size(8.dp)
            .background(
                Color(0xFF00E5B0),
                CircleShape
            )
    )

}