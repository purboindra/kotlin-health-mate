package com.example.healthmate.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.healthmate.ui.theme.GrayDark
import com.example.healthmate.util.VerticalSpacer

@Composable
fun MyActivityDaily(modifier: Modifier = Modifier) {
    
    
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        DateMyActivityDaily(
            modifier = modifier,
            title = "Kamis, 10 April",
            onNext = {},
            onPrev = {}
        )
        8.VerticalSpacer()
        ChartMyActivityDaily(modifier = modifier)
        8.VerticalSpacer()
        CardioStepInformation(modifier = modifier)
        18.VerticalSpacer()
        HorizontalDivider(modifier, 1.dp, GrayDark.copy(alpha = 0.6f))
        11.VerticalSpacer()
        Box(modifier = modifier.padding(horizontal = 10.dp)) {
            TextButton(onClick = {}) {
                Text(
                    "Lihat sumber data",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.Blue
                    )
                )
            }
        }
    }
}