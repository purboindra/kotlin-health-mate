package com.example.healthmate.ui.screen.exercise

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.health.connect.client.HealthConnectClient
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.healthmate.data.HealthConnectManager
import com.example.healthmate.data.SensorManager
import com.example.healthmate.ui.component.ActivityHealthDialogContent
import com.example.healthmate.ui.component.CardioInformation
import com.example.healthmate.ui.component.DailyGoal
import com.example.healthmate.ui.component.NonActiveActivityCard
import com.example.healthmate.ui.component.ProgressCircle
import com.example.healthmate.ui.component.RowIconWithText
import com.example.healthmate.ui.component.SyncWithHealthConnectCard
import com.example.healthmate.ui.dialog.ActivityHealthMateDialog
import com.example.healthmate.ui.icons.MyIconPack
import com.example.healthmate.ui.icons.myiconpack.Cardio
import com.example.healthmate.ui.icons.myiconpack.Foot
import com.example.healthmate.ui.navigation.Screen
import com.example.healthmate.ui.theme.GrayDark
import com.example.healthmate.ui.theme.MintGreen
import com.example.healthmate.util.HorizontalSpacer
import com.example.healthmate.util.VerticalSpacer

data class WeightData(
    val kg: String
)

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ExerciseScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    exerciseViewModel: ExerciseViewModel = hiltViewModel(),
    healthConnectManager: HealthConnectManager,
) {
    var loading by remember { mutableStateOf(false) }
    
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val context = LocalContext.current
    
    var showDialog by remember { mutableStateOf(false) }
    var contentIndex by remember { mutableIntStateOf(0) }
    
    val stepSensorManager = remember {
        SensorManager(context) {
            exerciseViewModel.updateStepCount(it)
        }
    }
    
    val healthConnectManager = remember {
        HealthConnectManager(context)
    }
    
    LaunchedEffect(Unit) {
        val healthConnectAvailable = healthConnectManager.availability
        
        if (healthConnectAvailable.intValue == HealthConnectClient.SDK_UNAVAILABLE) {
            Log.d("HealthConnectManager", "Health connect not available")
            navHostController.navigate(Screen.HealthConnectUnavailable.route)
            return@LaunchedEffect
        }
        
        loading = true
        exerciseViewModel.getWeight()
        loading = false
    }
    
    DisposableEffect(Unit) {
        stepSensorManager.registerListener()
        onDispose {
            exerciseViewModel.unregisterStepListener()
        }
    }
    
    if (showDialog) {
        ActivityHealthMateDialog(
            onDismiss = {
                showDialog = false
            },
            onButtonTap = {
                if (contentIndex == 2) {
                    showDialog = false
                } else {
                    contentIndex++
                }
                Log.d("ExerciseScreen", "Content Index: $contentIndex")
            },
            buttonText = if (contentIndex == 2) "Selesai" else "Berikutnya",
            content = {
                ActivityHealthDialogContent(
                    index = contentIndex
                )
            }
        )
    }
    
    LazyColumn {
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
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(onClick = {
                                contentIndex = 0
                                showDialog = true
                            })
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
                exerciseViewModel = exerciseViewModel
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                RowIconWithText(
                    icon = MyIconPack.Cardio,
                    text = "Poin Kardio",
                    contentDescription = "Poin Kardio",
                    iconColor = Color(0xFF44bbbd),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF44bbbd),
                    ),
                )
                17.HorizontalSpacer()
                RowIconWithText(
                    icon = MyIconPack.Foot, iconColor = Color(0xFF0BA6D5),
                    text = "Langkah",
                    contentDescription = "Langkah",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF0BA6D5),
                    ),
                )
            }
            
            12.VerticalSpacer()
            
            CardioInformation(
                exerciseViewModel = exerciseViewModel
            )
            
            DailyGoal()
            
            SyncWithHealthConnectCard()
            
            NonActiveActivityCard(
                onOpenSettings = {
                    val intent =
                        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                            data = Uri.fromParts(
                                "package",
                                context.packageName,
                                null
                            )
                        }
                    context.startActivity(intent)
                }
            )
            
            
            
            24.VerticalSpacer()
        }
    }
}