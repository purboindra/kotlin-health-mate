package com.example.healthmate.ui.component

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp


@Composable
fun GoalCircle(
    size: Dp = 200.dp,
    strokeWidth: Dp = 14.dp,
    topOffeset: Float,
    progressColor: Color = Color(0xFF673AB7),
    progress: Float = 0.001f,
    startAngle: Float = 270f,
) {
    Canvas(
        modifier = Modifier
            .size(size)
    ) {
        val stroke = 18.dp.toPx()
        val radius = size.toPx() / 2.2f
        
        val innerRadius = radius - stroke
        
        val strokeWidthPx = strokeWidth.toPx()
        val arcSize = size.toPx() - strokeWidthPx
        
        Log.d(
            "Goal Circule",
            "Inner radius: $innerRadius, $radius, $stroke, $arcSize, $strokeWidthPx, ${size.toPx()}"
        )
        
        withTransform({
            rotate(degrees = startAngle, pivot = center)
        }) {
            drawArc(
                color = progressColor.copy(alpha = 0.3f),
                startAngle = 0f,
                sweepAngle = 1f * 360,
                useCenter = false,
                topLeft = Offset(topOffeset, strokeWidthPx / 2),
                size = Size(arcSize, arcSize),
                style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)
            )
            
            drawArc(
                color = progressColor,
                startAngle = 0f,
                sweepAngle = progress * 360,
                useCenter = false,
                topLeft = Offset(topOffeset, strokeWidthPx / 2),
                size = Size(arcSize, arcSize),
                style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)
            )
        }
    }
}
