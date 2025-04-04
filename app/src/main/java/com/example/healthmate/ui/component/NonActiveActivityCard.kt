package com.example.healthmate.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.healthmate.ui.theme.GrayDark
import com.example.healthmate.util.VerticalSpacer

@Composable
fun NonActiveActivityCard(
    onOpenSettings: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            LabelIconRow(
                label = "Pemantauan aktivitas nonaktif", child = {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Close",
                        modifier = Modifier
                            .size(18.dp)
                            .padding(top = 3.dp),
                    )
                })
            RowIconWithText(
                icon = Icons.Outlined.Info,
                iconModifier = Modifier.size(12.dp),
                iconColor = Color.Blue,
                text = "Preferensi pemantauan",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = Color.Blue, fontWeight = FontWeight.W400
                ),
                contentDescription = "Info"
            )
            8.VerticalSpacer()
            Text(
                "Health Mate tidak akan memantau aktivitas Anda, tapi aplikasi yang terhubung masih bisa menambahkan data ke Health Mate kalau memiliki izin. Anda bisa mengelola aplikasi yang terhubung dan mengubah preferensi pemantauan Anda di Setelah.",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = GrayDark,
                    fontWeight = FontWeight.Normal,
                ),
            )
            12.VerticalSpacer()
            Text(
                "Buka Setelan",
                style = MaterialTheme.typography.labelLarge.copy(
                    color = Color.Blue,
                    fontWeight = FontWeight.W500
                ),
                modifier = Modifier.clickable(onClick = {
                    onOpenSettings()
                })
            )
        }
    }
}