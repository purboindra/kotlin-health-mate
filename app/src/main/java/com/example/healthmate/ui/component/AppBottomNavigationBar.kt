package com.example.healthmate.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.healthmate.data.BottomNavigationItem
import com.example.healthmate.ui.icons.MyIconPack
import com.example.healthmate.ui.icons.myiconpack.Analyticsup
import com.example.healthmate.ui.icons.myiconpack.Goal
import com.example.healthmate.ui.icons.myiconpack.Home
import com.example.healthmate.ui.icons.myiconpack.Profile
import com.example.healthmate.ui.navigation.Screen
import com.example.healthmate.ui.theme.GrayDark
import com.example.healthmate.ui.theme.Green

@Composable
fun AppBottomNavigationBar(
    selectedItem: String,
    onSelectedItem: (String) -> Unit
) {
    
    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedItem = {
                Icon(
                    imageVector = MyIconPack.Home,
                    contentDescription = "Home",
                    tint = Green,
                    modifier = Modifier.size(28.dp)
                )
            },
            unSelectedItem = {
                Icon(
                    imageVector = MyIconPack.Home,
                    contentDescription = "Home",
                    tint = GrayDark,
                    modifier = Modifier.size(28.dp)
                )
            },
            hasNew = false,
            route = Screen.Home.route
        ),
        BottomNavigationItem(
            title = "Goal",
            selectedItem = {
                Icon(
                    imageVector = MyIconPack.Goal,
                    contentDescription = "Goal",
                    tint = Green,
                    modifier = Modifier.size(28.dp)
                )
            },
            unSelectedItem = {
                Icon(
                    imageVector = MyIconPack.Goal,
                    contentDescription = "Goal",
                    tint = GrayDark,
                    modifier = Modifier.size(28.dp)
                )
            },
            hasNew = false,
            route = Screen.Goal.route
        ),
        BottomNavigationItem(
            title = "Exercise",
            selectedItem = {
                Icon(
                    imageVector = MyIconPack.Analyticsup,
                    contentDescription = "Exercise",
                    tint = Green,
                    modifier = Modifier.size(28.dp)
                )
            },
            unSelectedItem = {
                Icon(
                    imageVector = MyIconPack.Analyticsup,
                    contentDescription = "Exercise",
                    tint = GrayDark,
                    modifier = Modifier.size(28.dp)
                )
            },
            hasNew = false,
            route = Screen.Exercise.route
        ),
        BottomNavigationItem(
            title = "Profile",
            selectedItem = {
                Icon(
                    imageVector = MyIconPack.Profile,
                    contentDescription = "Profile",
                    tint = Green,
                    modifier = Modifier.size(28.dp)
                )
            },
            unSelectedItem = {
                Icon(
                    imageVector = MyIconPack.Profile,
                    contentDescription = "Profile",
                    tint = GrayDark,
                    modifier = Modifier.size(28.dp)
                )
            },
            hasNew = false,
            route = Screen.Exercise.route
        ),
    )
    
    NavigationBar(containerColor = Color.White) {
        items.forEachIndexed { _, bottomNavigationItem ->
            NavigationBarItem(
                selected = selectedItem == bottomNavigationItem.route,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White,
                    selectedTextColor = Green,
                    unselectedTextColor = GrayDark,
                    
                    ),
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
                        if (bottomNavigationItem.route == selectedItem) {
                            bottomNavigationItem.selectedItem()
                        } else {
                            bottomNavigationItem.unSelectedItem()
                        }
                    }
                },
                label = {
                    Text(
                        bottomNavigationItem.title,
                        )
                }
            )
            
        }
    }
    
}