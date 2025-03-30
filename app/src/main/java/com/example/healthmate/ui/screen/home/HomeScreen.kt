package com.example.healthmate.ui.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthmate.ui.component.HomeScreenHeader

@Composable
fun HomeScreen(modifier: Modifier) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        item {
            HomeScreenHeader(
                modifier = modifier
            )
        }
    }
}