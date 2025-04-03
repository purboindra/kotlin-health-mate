package com.example.healthmate.ui.screen.exercise

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.healthmate.data.HealthConnectManager
import com.example.healthmate.ui.component.CardioInformation
import com.example.healthmate.ui.component.DailyGoal
import com.example.healthmate.ui.component.ProgressCircle
import com.example.healthmate.ui.component.RowIconWithText
import com.example.healthmate.ui.icons.MyIconPack
import com.example.healthmate.ui.icons.myiconpack.Cardio
import com.example.healthmate.ui.icons.myiconpack.Foot
import com.example.healthmate.ui.theme.GrayDark
import com.example.healthmate.ui.theme.MintGreen
import com.example.healthmate.util.HorizontalSpacer
import com.example.healthmate.util.VerticalSpacer

data class WeightData(
    val kg: String
)

@Composable
fun ExerciseScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    exerciseViewModel: ExerciseViewModel = hiltViewModel(),
    healthConnectManager: HealthConnectManager,
) {
    
    val coroutineScope = rememberCoroutineScope()
    val weight by exerciseViewModel.weightQuery.collectAsState()
    val weightList by exerciseViewModel.weightList.collectAsState()
    var loading by remember { mutableStateOf(false) }
    val backgroundReadAvailable by exerciseViewModel.backgroundReadAvailable.collectAsState()
    val historyReadAvailable by exerciseViewModel.historyReadAvailable.collectAsState()
    
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    
    val outerSize = screenHeight * 0.25f
    val innerSize = screenHeight * 0.20f
    
    
    val permissionLauncher = rememberLauncherForActivityResult(
        healthConnectManager.requestPermissionsActivityContract()
    ) {
        Log.d("HealthConnect", "Permissions granted successfully!")
    }
    
    LaunchedEffect(Unit) {
        loading = true
        exerciseViewModel.getWeight()
        loading = false
    }
    
    LazyColumn(
        modifier = Modifier
            .safeContentPadding()
            .padding(horizontal = 16.dp),
    ) {
        item {
            
            val heightProgressCircle = screenHeight * 0.25f
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                /// HEADER
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Outlined.Info,
                        contentDescription = "Info",
                        tint = GrayDark,
                        modifier = Modifier.size(24.dp)
                    )
                    8.HorizontalSpacer()
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(MintGreen)
                    
                    )
                }
            }
            
            10.VerticalSpacer()
            
            ProgressCircle(
                heightProgressCircle = heightProgressCircle,
            )
            5.VerticalSpacer()
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                RowIconWithText(
                    icon = MyIconPack.Cardio,
                    text = "Poin Kardio",
                    contentDescription = "Poin Kardio",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                )
                17.HorizontalSpacer()
                RowIconWithText(
                    icon = MyIconPack.Foot,
                    text = "Langkah",
                    contentDescription = "Langkah",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                )
            }
            
            12.VerticalSpacer()
            
            CardioInformation()
            
            12.VerticalSpacer()
            
            DailyGoal()
            
        }
    }
}