package com.example.healthmate.util

import androidx.lifecycle.ViewModel
import com.example.healthmate.data.MapManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val mapManager: MapManager) :
    ViewModel() {
    
    
    
    
}