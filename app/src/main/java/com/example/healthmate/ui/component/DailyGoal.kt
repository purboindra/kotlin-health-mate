package com.example.healthmate.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.healthmate.ui.theme.GrayDark
import com.example.healthmate.util.HorizontalSpacer
import com.example.healthmate.util.VerticalSpacer
import com.example.healthmate.util.dpToFloat

data class DailyGoalItem(
    val label: String,
    val outerProgress: Float,
    val innerProgress: Float,
)

@Composable
fun DailyGoal() {
    
    val dailyGoals = listOf<DailyGoalItem>(
        DailyGoalItem(
            label = "J",
            outerProgress = 0.3f,
            innerProgress = 0.1f,
        ),
        DailyGoalItem(
            label = "S",
            outerProgress = 0.3f,
            innerProgress = 0.1f,
        ),
        DailyGoalItem(
            label = "M",
            outerProgress = 0.3f,
            innerProgress = 0.1f,
        ),
        DailyGoalItem(
            label = "S",
            outerProgress = 0.3f,
            innerProgress = 0.1f,
        ),
        DailyGoalItem(
            label = "S",
            outerProgress = 0.3f,
            innerProgress = 0.1f,
        ),
        DailyGoalItem(
            label = "R",
            outerProgress = 0.3f,
            innerProgress = 0.1f,
        ),
        DailyGoalItem(
            label = "K",
            outerProgress = 0.3f,
            innerProgress = 0.1f,
        )
    )
    
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
            modifier = Modifier
                .padding(12.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Target Harian",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.W600,
                    )
                )
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Detail",
                    modifier = Modifier.size(18.dp)
                )
            }
            3.VerticalSpacer()
            Text(
                "7 hari terakhir",
                style = MaterialTheme.typography.labelSmall.copy(color = GrayDark)
            )
            
            8.VerticalSpacer()
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                
                ) {
                    Text(
                        "0/7",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.Blue,
                            fontWeight = FontWeight.W600,
                        )
                    )
                    Text(
                        "tercapai",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.Blue,
                            fontWeight = FontWeight.Normal,
                        )
                    )
                }
                10.HorizontalSpacer()
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(dailyGoals) { item ->
                        Column(
                            modifier = Modifier
                                .height(48.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                contentAlignment = Alignment.Center
                            ) {
                                GoalCircle(
                                    size = 36.dp,
                                    progress = item.outerProgress,
                                    strokeWidth = 3.dp,
                                    topOffeset = (36.dp / 8f).dpToFloat(),
                                    progressColor = Color(0xFF9DD1D2),
                                )
                                GoalCircle(
                                    size = 24.dp,
                                    progress = item.innerProgress,
                                    strokeWidth = 2.dp,
                                    topOffeset = (24.dp / 8f).dpToFloat(),
                                    progressColor = Color(0xFF0BA6D5),
                                )
                            }
                            Text(
                                item.label,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = GrayDark
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}