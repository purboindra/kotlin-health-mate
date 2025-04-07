package com.example.healthmate.ui.screen.walk

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.healthmate.ui.component.HeaderWalk
import com.example.healthmate.ui.component.OsmMapView
import com.example.healthmate.ui.component.WalkInformation

@Composable
fun WalkScreen(
    navHostController: NavHostController,
    walkViewModel: WalkViewModel = hiltViewModel<WalkViewModel>()
) {
    
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.Gray.copy(alpha = 0.3f)),
        ) {
            
            OsmMapView(modifier = Modifier)
            
            HeaderWalk(
                navHostController = navHostController,
                modifier = Modifier
            )
            
            WalkInformation(
                modifier = Modifier,
                walkViewModel = walkViewModel
            )
        }
    }
    
}