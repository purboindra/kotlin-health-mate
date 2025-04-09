package com.example.healthmate.ui.screen.my_activity

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.healthmate.ui.component.PrimaryTextTabs
import com.example.healthmate.util.HorizontalSpacer
import com.example.healthmate.util.VerticalSpacer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyActivityScreen(navHostController: NavHostController) {
    
    var tabIndex by remember { mutableIntStateOf(0) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(
                                onClick = {
                                    navHostController.popBackStack()
                                }
                            )
                    )
                },
                title = {
                    Text(
                        "Aktivitas Saya",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                actions = {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add",
                        modifier = Modifier.size(24.dp)
                    )
                    12.HorizontalSpacer()
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = "More",
                        modifier = Modifier.size(24.dp)
                    )
                    8.HorizontalSpacer()
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            PrimaryTextTabs(
                onTabSelect = {
                    tabIndex = it
                },
                tabSelectedIndex = tabIndex
            )
            10.VerticalSpacer()
            when (tabIndex) {
                0 -> {
                    Text(
                        "Tab 1",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                
                1 -> {
                    Text(
                        "Tab 2",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                
                else -> {
                    Text(
                        "Tab 3",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
    
}