package com.example.healthmate.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.healthmate.ui.theme.BlackPrimary
import com.example.healthmate.ui.theme.GrayDark
import com.example.healthmate.ui.theme.GrayLight
import com.example.healthmate.ui.theme.GrayLighter
import com.example.healthmate.ui.theme.MintGreen
import com.example.healthmate.ui.theme.White
import com.example.healthmate.util.VerticalSpacer


data class WeeklyHoursScheduleItem(
    val hasComplete: Boolean = false,
    val label: String,
    val duration: Int
)

@Composable
fun WeeklyHoursSchedule(
    modifier: Modifier = Modifier,
    schedule: List<WeeklyHoursScheduleItem>
) {
    
    val totalHours = schedule.fold(0) { accumulator, element ->
        accumulator + element.duration
    }
    
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onSurface,
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = 8.dp,
                vertical = 12.dp
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "Hours",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                    )
                )
                Text(
                    "$totalHours hours",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Normal,
                    )
                )
            }
            10.VerticalSpacer()
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.height(224.dp)
            ) {
                items(
                    schedule
                ) { item ->
                    
                    val calculatedHeight =
                        ((item.duration - 5 + 5 - 1) * 224 / 5).toFloat().dp
                    
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom,
                        modifier = Modifier.height(224.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .width(48.dp)
                                .height(calculatedHeight)
                                .clip(
                                    RoundedCornerShape(8.dp)
                                )
                                .background(
                                    if (item.duration != 0) BlackPrimary else GrayLighter,
                                )
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            Text(
                                "${item.duration}hr",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = if (item.duration != 0) MintGreen else Color.Transparent
                                )
                            )
                        }
                        8.VerticalSpacer()
                        Text(
                            item.label,
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = Color.Black
                            )
                        )
                    }
                }
            }
        }
    }
}

