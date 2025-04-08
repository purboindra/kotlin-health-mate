package com.example.healthmate.ui.screen

import android.graphics.BitmapFactory
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.healthmate.ui.navigation.Screen

@Composable
fun HealthConnectUnavailable(navHostController: NavHostController) {
    
    val context = LocalContext.current
    
    val assetManager = context.assets
    val healthConnectInputStream =
        assetManager.open("images/health_connect.png")
    val bitmap =
        remember { BitmapFactory.decodeStream(healthConnectInputStream) }
    val imageBitmap = remember { bitmap.asImageBitmap() }
    
    BackHandler {
        navHostController.navigate(Screen.Main.route) {
            popUpTo(0) { inclusive = true }
        }
    }
    
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
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