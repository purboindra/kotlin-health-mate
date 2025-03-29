package com.example.healthmate.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.healthmate.data.BottomNavigationItem
import com.example.healthmate.ui.navigation.Screen

private val items = listOf(
    BottomNavigationItem(
        title = "Home",
        selectedItem = Icons.Filled.Home,
        unSelectedItem = Icons.Outlined.Home,
        hasNew = false,
        route = Screen.Home.route
    ),
    BottomNavigationItem(
        title = "Goal",
        selectedItem = Icons.Filled.CheckCircle,
        unSelectedItem = Icons.Outlined.CheckCircle,
        hasNew = false,
        route = Screen.Goal.route
    ),
    BottomNavigationItem(
        title = "Exercise",
        selectedItem = Icons.Filled.AddCircle,
        unSelectedItem = Icons.Outlined.AddCircle,
        hasNew = false,
        route = Screen.Exercise.route
    ),
    BottomNavigationItem(
        title = "Profile",
        selectedItem = Icons.Filled.Person,
        unSelectedItem = Icons.Outlined.Person,
        hasNew = false,
        route = Screen.Exercise.route
    ),
)

@Composable
fun AppBottomNavigationBar(
    selectedItem: String,
    onSelectedItem: (String) -> Unit
) {
    NavigationBar {
        items.forEachIndexed { _, bottomNavigationItem ->
            NavigationBarItem(
                selected = selectedItem == bottomNavigationItem.route,
                onClick = { onSelectedItem(bottomNavigationItem.route) },
                icon = {
                    BadgedBox(
                        badge = {
                            if (bottomNavigationItem.badgeCount != null) {
                                Badge {
                                    Text(text = bottomNavigationItem.badgeCount.toString())
                                }
                            } else if (bottomNavigationItem.hasNew) {
                                Badge()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (bottomNavigationItem.route == selectedItem) bottomNavigationItem.selectedItem else bottomNavigationItem.unSelectedItem,
                            contentDescription = bottomNavigationItem.title
                        )
                    }
                },
                alwaysShowLabel = false,
                label = {
                    bottomNavigationItem.title
                }
            )
            
        }
    }
    
}