package com.example.healthmate.ui.dialog


import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

@Composable
fun PermissionHandler(
    onRequestPermissions: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    
    val context = LocalContext.current
    
    if (showDialog) {
        PermissionRationaleDialog(
            onDismiss = {
                showDialog = false
                Toast.makeText(
                    context,
                    "Permission required to proceed.",
                    Toast.LENGTH_SHORT
                ).show()
            },
            onConfirm = {
                showDialog = false
                onRequestPermissions()
            }
        )
    }
    
    // Example button to trigger it
    Button(onClick = { showDialog = true }) {
        Text("Request Permissions")
    }
}
