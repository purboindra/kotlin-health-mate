package com.example.healthmate.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.healthmate.ui.theme.GrayDark
import com.example.healthmate.ui.theme.Green
import com.example.healthmate.ui.theme.MintGreen
import com.example.healthmate.util.HorizontalSpacer
import com.example.healthmate.util.VerticalSpacer

@Composable
fun CardioStepInformation(modifier: Modifier = Modifier) {
    Column (modifier = modifier.padding(
        horizontal = 18.dp
    )) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MintGreen.copy(alpha = 0.2f)
            ) {
                Text(
                    "Poin Kardio",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Green,
                    ),
                    modifier = modifier.padding(
                        horizontal = 14.dp,
                        vertical = 8.dp
                    ),
                )
            }
            8.HorizontalSpacer()
            Surface(
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, GrayDark),
//                color = Color.Blue.copy(alpha = 0.2f)
            ) {
                Text(
                    "Langkah",
                    style = MaterialTheme.typography.titleMedium.copy(
                    
                    ),
                    modifier = modifier.padding(
                        horizontal = 14.dp,
                        vertical = 8.dp
                    ),
                )
            }
        }
        18.VerticalSpacer()
        Text(
            "Anda mendapatkan skor Poin Kardio untuk setiap menut aktivitas yang memompa jantung Anda, speerti jalan cepat. Tingkatkan intensitas untuk mendapatkan skor lebih banyak.",
            style = MaterialTheme.typography.titleSmall.copy(
                color = GrayDark,
                fontWeight = FontWeight.Normal,
            )
        )
    }
}