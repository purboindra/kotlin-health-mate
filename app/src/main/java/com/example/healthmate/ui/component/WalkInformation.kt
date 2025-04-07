package com.example.healthmate.ui.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.healthmate.ui.icons.MyIconPack
import com.example.healthmate.ui.icons.myiconpack.Foot
import com.example.healthmate.ui.screen.walk.WalkViewModel
import com.example.healthmate.ui.theme.BlackPrimary
import com.example.healthmate.ui.theme.MintGreen
import com.example.healthmate.util.VerticalSpacer

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun WalkInformation(
    modifier: Modifier = Modifier, walkViewModel: WalkViewModel
) {
    
    val stepsCount by walkViewModel.stepsCount.collectAsStateWithLifecycle()
    val distanceMeters by walkViewModel.distanceMeters.collectAsStateWithLifecycle()
    val calories by walkViewModel.calories.collectAsStateWithLifecycle()
    val duration by walkViewModel.durationMovement.collectAsStateWithLifecycle()
    
    Box(
        modifier = modifier
            .fillMaxSize()
            .safeContentPadding(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = 18.dp
                )
                .clip(RoundedCornerShape(12.dp))
                .background(
                    BlackPrimary
                )
        ) {
            Column(
                modifier = modifier.padding(
                    horizontal = 12.dp,
                    vertical = 18.dp
                )
            ) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Time",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = modifier.size(24.dp)
                    )
                }
                
                12.VerticalSpacer()
                
                WalkTimeInformation(
                    modifier = modifier,
                    walkViewModel = walkViewModel
                )
                
                12.VerticalSpacer()
                
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 48.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    WalkItemInformation(
                        modifier = modifier,
                        label = "Steps",
                        value = stepsCount.toString(),
                        imageVector = MyIconPack.Foot,
                        backgroundColor = MintGreen,
                        contentDescription = "Steps"
                    )
                    
                    WalkItemInformation(
                        modifier = modifier,
                        label = "Minutes",
                        value = duration?.toMinutes().toString(),
                        icon = {
                            Icon(
                                Icons.Outlined.Info,
                                tint = Color.White,
                                modifier = modifier.size(24.dp),
                                contentDescription = "Minutes",
                            )
                        },
                        backgroundColor = Color.Blue,
                    )
                }
                
                12.VerticalSpacer()
                
                ElevatedButton(
                    onClick = {
                    },
                    modifier = modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = Color.White
                    ),
                ) {
                    duration?.seconds?.let {
                        Text(
                            if (it > 0) "Stop" else "Start",
                            color = BlackPrimary
                        )
                    }
                }
            }
        }
    }
}