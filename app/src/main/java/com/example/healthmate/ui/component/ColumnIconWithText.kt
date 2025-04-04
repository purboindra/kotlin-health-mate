package com.example.healthmate.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.healthmate.ui.icons.MyIconPack
import com.example.healthmate.ui.icons.myiconpack.Cardio

@Composable
fun ColumnIconWithText(
    icon: ImageVector,
    iconDescription: String,
    iconModifier: Modifier = Modifier.size(18.dp),
    title: String,
    description: String,
) {
    Column(
        modifier = Modifier.fillMaxWidth(0.65f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            icon,
            contentDescription = iconDescription,
            modifier = iconModifier
        )
        Text(
            title,
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.W500,
            )
        )
        Text(
            description,
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center
        )
    }
}