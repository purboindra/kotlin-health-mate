package com.example.healthmate.ui.dialog


import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@Composable
fun ActivityHealthMateDialog(
    onDismiss: () -> Unit,
    onButtonTap: () -> Unit,
    content: @Composable (() -> Unit),
    buttonText: String
) {
    AlertDialog(
        modifier = Modifier
            .fillMaxHeight(0.6f)
            .fillMaxWidth(0.8f),
        onDismissRequest = onDismiss,
        text = {
            content()
        },
        confirmButton = {
            ElevatedButton(
                onClick = onButtonTap,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .width(124.dp),
                content = {
                    Text(
                        buttonText,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.W500,
                            color = Color.Blue,
                        )
                    )
                },
            )
        },
        properties = DialogProperties(usePlatformDefaultWidth = false),
        shape = RoundedCornerShape(2.dp),
    )
}