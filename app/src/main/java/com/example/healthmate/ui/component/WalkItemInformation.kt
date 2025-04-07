package com.example.healthmate.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.healthmate.ui.icons.MyIconPack
import com.example.healthmate.ui.icons.myiconpack.Foot
import com.example.healthmate.ui.theme.Green
import com.example.healthmate.util.HorizontalSpacer

@Composable
fun WalkItemInformation(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    backgroundColor: Color,
    imageVector: ImageVector? = null,
    icon: @Composable (() -> Unit)? = null,
    contentDescription: String? = null,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        
        Box(
            modifier = modifier
                .size(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            if (icon != null) {
                icon()
            } else {
                Image(
                    imageVector = imageVector ?: MyIconPack.Foot,
                    contentDescription = contentDescription ?: "",
                    modifier = modifier.size(24.dp),
                )
            }
        }
        
        5.HorizontalSpacer()
        
        Column {
            Text(
                value,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                label,
                style = MaterialTheme.typography.labelSmall.copy(
                    color = Color.White,
                )
            )
        }
    }
}