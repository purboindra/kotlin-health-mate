package com.example.healthmate.ui.component

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.healthmate.ui.theme.BlackPrimary
import com.example.healthmate.ui.theme.Gray
import com.example.healthmate.ui.theme.GrayDark
import com.example.healthmate.ui.theme.GrayLight
import com.example.healthmate.ui.theme.GrayLighter
import com.example.healthmate.ui.theme.MintGreen
import com.example.healthmate.ui.theme.White
import com.example.healthmate.util.VerticalSpacer

data class WeeklyScheduleItem(
    val hasComplete: Boolean = false,
    val label: String
)

@Composable
fun WeeklySchedule(
    modifier: Modifier = Modifier,
    schedule: List<WeeklyScheduleItem>
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardColors(
            contentColor = Color.Black,
            containerColor = Color.White,
            disabledContentColor = Color.White,
            disabledContainerColor = Gray
        )
    ) {
        Column(
            modifier = modifier.padding(
                horizontal = 8.dp,
                vertical = 12.dp
            )
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "This Week",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                    )
                )
                Text(
                    "4/7 Days",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Normal,
                    )
                )
            }
            10.VerticalSpacer()
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier.height(82.dp)
            ) {
                items(
                    schedule
                ) { item ->
                    Surface(
                        shape = RoundedCornerShape(100),
                        color = if (item.hasComplete) BlackPrimary else GrayLighter,
                        modifier = modifier
                            .width(48.dp)
                            .fillMaxHeight()
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween,
                            modifier = modifier.padding(vertical = 8.dp),
                        ) {
                            Text(
                                item.label,
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = if (item.hasComplete) White else GrayDark
                                )
                            )
                            8.VerticalSpacer()
                            Icon(
                                Icons.Default.CheckCircle,
                                contentDescription = "Check",
                                modifier = Modifier.size(24.dp),
                                tint = if (item.hasComplete) MintGreen else GrayLight
                            )
                        }
                    }
                }
            }
        }
    }
}

