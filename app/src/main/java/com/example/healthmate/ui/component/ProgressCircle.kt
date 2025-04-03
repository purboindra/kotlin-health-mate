package com.example.healthmate.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.healthmate.ui.theme.Green
import com.example.healthmate.util.dpToFloat

@Composable
fun ProgressCircle(
    heightProgressCircle: Dp
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        contentAlignment = Alignment.Center,
    ) {
        GoalCircle(
            topOffeset = (heightProgressCircle / 8f).dpToFloat(),
            progressColor = Color(0xFF9DD1D2),
        )
        
        GoalCircle(
            size = 150.dp,
            topOffeset = (heightProgressCircle / 8f).dpToFloat(),
            progressColor = Color(0xFF0BA6D5),
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                "0",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 48.sp,
                    color = Color(0xFF0BA6D5),
                )
            )
            Text(
                "0", style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Green
                )
            )
        }
    }
}