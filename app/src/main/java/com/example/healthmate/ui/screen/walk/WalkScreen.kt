package com.example.healthmate.ui.screen.walk

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.healthmate.data.SensorManager
import com.example.healthmate.ui.component.HeaderWalk
import com.example.healthmate.ui.component.OsmMapView
import com.example.healthmate.ui.component.WalkInformation
import com.example.healthmate.ui.screen.exercise.ExerciseViewModel

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun WalkScreen(
    navHostController: NavHostController,
    walkViewModel: WalkViewModel = hiltViewModel<WalkViewModel>(),
    exerciseViewModel: ExerciseViewModel = hiltViewModel<ExerciseViewModel>()
) {
    
    val context = LocalContext.current
    
    val sensorManager = remember {
        SensorManager(
            context = context, onStepUpdate = {}
        )
    }
    
    
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.Gray.copy(alpha = 0.3f)),
        ) {
            
            OsmMapView(modifier = Modifier, walkViewModel = walkViewModel)
            
            HeaderWalk(
                navHostController = navHostController,
                modifier = Modifier
            )
            
            if (sensorManager.hasStepSensor()) WalkInformation(
                modifier = Modifier,
                walkViewModel = walkViewModel,
                exerciseViewModel = exerciseViewModel
            )
            
        }
    }
    
}