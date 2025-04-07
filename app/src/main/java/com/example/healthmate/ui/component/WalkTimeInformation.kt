package com.example.healthmate.ui.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.healthmate.ui.screen.walk.WalkViewModel

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun WalkTimeInformation(
    modifier: Modifier = Modifier,
    walkViewModel: WalkViewModel
) {
    
    val duration by walkViewModel.durationMovement.collectAsStateWithLifecycle()
    
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TimeItem(
            modifier = modifier,
            time = duration?.toHours().toString(),
            label = "Hour"
        )
        
        Text(
            ":",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 36.sp,
            )
        )
        TimeItem(
            modifier = modifier,
            time = duration?.toMinutes().toString(),
            label = "Minute"
        )
        
        Text(
            ":",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 36.sp,
            )
        )
        
        TimeItem(
            modifier = modifier,
            time = duration?.toSeconds().toString(),
            label = "Second"
        )
        
    }
}