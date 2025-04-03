package com.example.healthmate.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.healthmate.util.HorizontalSpacer

@Composable
fun RowIconWithText(
    icon: ImageVector,
    contentDescription: String,
    iconModifier: Modifier = Modifier.size(24.dp),
    text: String,
    style: TextStyle = LocalTextStyle.current
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            icon,
            contentDescription = contentDescription,
            modifier = iconModifier,
        )
        5.HorizontalSpacer()
        Text(
            text,
            style = style,
        )
    }
}