package com.example.healthmate.data

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val title: String,
    val selectedItem: ImageVector,
    val unSelectedItem: ImageVector,
    val hasNew: Boolean,
    val badgeCount: Int? = null,
    val route: String
)