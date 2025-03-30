package com.example.healthmate.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.healthmate.ui.icons.MyIconPack
import com.example.healthmate.ui.icons.myiconpack.Analyticsup

@Composable
fun HomeScreenHeader(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Text(
                "Welcome Grace",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
        
        IconRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            icons = listOf(
                IconRowData(
                    imageVector = MyIconPack.Analyticsup,
                    contentDescription = "Analytics Up",
                    modifier = Modifier.size(28.dp),
                ),
                IconRowData(
                    Icons.Outlined.Notifications,
                    contentDescription = "Notification",
                    modifier = Modifier.size(28.dp),
                )
            ),
        )
    }
}