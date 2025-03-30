package com.example.healthmate.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class IconRowData(
    val imageVector: ImageVector,
    val contentDescription: String?,
    val modifier: Modifier,
    val tint: Color? = null,
)

@Composable
fun IconRow(
    icons: List<IconRowData>,
    modifier: Modifier = Modifier,
    reverseLayout: Boolean = false,
    horizontalArrangement: Arrangement.Horizontal =
        if (!reverseLayout) Arrangement.Start else Arrangement.End,
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        reverseLayout = reverseLayout
    ) {
        items(icons) { icon ->
            Icon(
                imageVector = icon.imageVector,
                modifier = icon.modifier,
                tint = icon.tint ?: LocalContentColor.current,
                contentDescription = icon.contentDescription
            )
        }
    }
}