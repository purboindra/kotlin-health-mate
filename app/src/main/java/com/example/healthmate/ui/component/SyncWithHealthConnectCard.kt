package com.example.healthmate.ui.component

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.healthmate.ui.theme.GrayDark
import com.example.healthmate.util.HorizontalSpacer
import com.example.healthmate.util.VerticalSpacer

@Composable
fun SyncWithHealthConnectCard() {
    
    val context = LocalContext.current
    
    val assetManager = context.assets
    val healthConnectInputStream =
        assetManager.open("images/health_connect.png")
    val bitmap =
        remember { BitmapFactory.decodeStream(healthConnectInputStream) }
    val imageBitmap = remember { bitmap.asImageBitmap() }
    
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
        )
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    "Sinkronkan Health Mate dengan Health Connect",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.W600,
                    ),
                    modifier = Modifier.weight(1f),
                )
                8.HorizontalSpacer()
                Icon(
                    Icons.Default.Clear,
                    contentDescription = "Close",
                    modifier = Modifier
                        .size(18.dp)
                        .padding(top = 3.dp),
                )
            }
            
            12.VerticalSpacer()
            
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    "Gunakan statistik secara bersama antara Health Mate dan aplikasi Anda yang lain, misalnya data kalori, detak jantung, dan pengukuran tubuh",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = GrayDark,
                        fontWeight = FontWeight.Normal,
                    ),
                    modifier = Modifier.weight(1f),
                )
                8.HorizontalSpacer()
                Image(
                    bitmap = imageBitmap,
                    contentDescription = "Health Connect Logo",
                    modifier = Modifier
                        .size(56.dp),
                )
            }
            
        }
    }
}