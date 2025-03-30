package com.example.healthmate.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.healthmate.ui.theme.GrayDark
import com.example.healthmate.ui.theme.Green
import com.example.healthmate.ui.theme.MintGreen
import com.example.healthmate.util.VerticalSpacer


@Composable
fun TodayMeal() {
    
    val meals = listOf(
        TodayMealItem(
            label = "Oatmeal with Berries",
            detail = "350 kcal, 10g protein, 60g Carbs, 5g Fat",
            type = MealType.BREAKFAST
        ),
        TodayMealItem(
            label = "Grilled Chicken Salah",
            detail = "350 kcal, 10g protein, 60g Carbs, 5g Fat",
            type = MealType.LUNCH
        )
    )
    
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
                    "Today's Meal",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                    )
                )
                Text(
                    "Meal",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Normal,
                    )
                )
            }
            10.VerticalSpacer()
            Column() {
                meals.forEachIndexed { index, item ->
                    Surface(
                        modifier = Modifier
                            .padding(
                                horizontal = 8.dp, vertical = 5.dp
                            )
                    ) {
                        Column(
                            modifier = Modifier.padding(
                                horizontal = 8.dp,
                                vertical = 5.dp
                            )
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(item.label)
                                Text(
                                    when (item.type) {
                                        MealType.BREAKFAST -> "Breakfast"
                                        MealType.LUNCH -> "Lunch"
                                        MealType.DINNER -> "Dinner"
                                        MealType.SNACKS -> "Snacks"
                                    },
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Green
                                    )
                                )
                            }
                            3.VerticalSpacer()
                            Text(item.detail, style = MaterialTheme.typography.labelSmall.copy(
                                color = GrayDark
                            ))
                        }
                    }
                    
                }
            }
        }
    }
}

enum class MealType {
    BREAKFAST, LUNCH, DINNER, SNACKS
}

data class TodayMealItem(
    val label: String,
    val detail: String,
    val type: MealType,
)