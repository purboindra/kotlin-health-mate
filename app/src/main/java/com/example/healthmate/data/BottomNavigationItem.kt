package com.example.healthmate.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val title: String,
    val selectedItem: @Composable () -> Unit,
    val unSelectedItem: @Composable () -> Unit,
    val hasNew: Boolean,
    val badgeCount: Int? = null,
    val route: String
)