package com.example.healthmate.ui.component


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.healthmate.util.VerticalSpacer

@Composable
fun MyActivityMonthly(modifier: Modifier = Modifier) {
    
    
    Column(modifier = modifier.fillMaxSize()) {
        DateMyActivityDaily(
            modifier = modifier,
            title = "April 2025",
            onNext = {},
            onPrev = {}
        )
        8.VerticalSpacer()
        
    }
}