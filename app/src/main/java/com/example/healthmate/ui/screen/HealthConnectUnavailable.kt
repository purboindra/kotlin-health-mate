package com.example.healthmate.ui.screen

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun HealthConnectUnavailable() {
    
    val context = LocalContext.current
    
    val assetManager = context.assets
    val healthConnectInputStream =
        assetManager.open("images/health_connect.png")
    val bitmap =
        remember { BitmapFactory.decodeStream(healthConnectInputStream) }
    val imageBitmap = remember { bitmap.asImageBitmap() }
    
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Image(
                    bitmap = imageBitmap,
                    contentDescription = "Health Connect Logo",
                    modifier = Modifier
                        .size(72.dp),
                )
                
                Text(
                    "Health Connect Unavailable",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            
        }
    }
}