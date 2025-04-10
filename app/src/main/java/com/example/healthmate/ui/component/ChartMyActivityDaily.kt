package com.example.healthmate.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottom
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStart
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.core.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.core.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries

@Composable
fun ChartMyActivityDaily(modifier: Modifier = Modifier) {
    
    val modelProducer = remember { CartesianChartModelProducer() }
    LaunchedEffect(Unit) {
        modelProducer.runTransaction {
            lineSeries {
                series(
                    13,
                    8,
                    7,
                    12,
                    0,
                    1,
                    15,
                    14,
                    0,
                    11,
                    6,
                    12,
                    0,
                    11,
                    12,
                    11
                )
            }
        }
    }
    
    CartesianChartHost(
        chart = rememberCartesianChart(
            rememberLineCartesianLayer(),
            startAxis = VerticalAxis.rememberStart(),
            bottomAxis = HorizontalAxis.rememberBottom(),
        ),
        modelProducer = modelProducer,
        modifier = modifier,
    )
}