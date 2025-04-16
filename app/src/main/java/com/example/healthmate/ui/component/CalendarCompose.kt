package com.example.healthmate.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.time.YearMonth

data class CalendarUiState(
    val yearMonth: YearMonth,
    val dates: List<Date>
) {
    data class Date(
        val dayOfMonth: String,
        val isSelected: Boolean
    )
}


@Composable
fun CalendarCompose(
    dates: List<CalendarUiState.Date>,
    onDateClickListener: (CalendarUiState.Date) -> Unit,
    modifier: Modifier = Modifier
) {
    
    Column {
        var index = 0
        repeat(6) {
            if (index >= dates.size) return@repeat
//            Row {
//                repeat(7) {
//                    val item =
//                        if (index < dates.size) dates[index] else CalendarUiState.Date.
//                    ContentItem(
//                        date = item,
//                        onClickListener = onDateClickListener,
//                        modifier = Modifier.weight(1f)
//                    )
//                    index++
//                }
//            }
        }
    }
    
    
}


@Composable
fun ContentItem(
    date: CalendarUiState.Date,
    onClickListener: (CalendarUiState.Date) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = if (date.isSelected) {
                    MaterialTheme.colorScheme.secondaryContainer
                } else {
                    Color.Transparent
                }
            )
            .clickable {
                onClickListener(date)
            }
    ) {
        Text(
            text = date.dayOfMonth,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(10.dp)
        )
    }
}