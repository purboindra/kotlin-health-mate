package com.example.healthmate.ui.component


import android.preference.PreferenceManager
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.healthmate.data.MapManager
import com.example.healthmate.ui.screen.walk.WalkViewModel
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline
import java.io.File


@Composable
fun OsmMapView(modifier: Modifier = Modifier, walkViewModel: WalkViewModel) {
    val context = LocalContext.current
    
    val center = remember { mutableStateOf(GeoPoint(0.0, 0.0)) }
    val zoomLevel = remember { mutableDoubleStateOf(15.0) }
    
    val locations by walkViewModel.locations.collectAsStateWithLifecycle()
    
    val mapManager = remember {
        MapManager(context = context)
    }
    
    LaunchedEffect(Unit) {
        Configuration.getInstance().load(
            context,
            PreferenceManager.getDefaultSharedPreferences(context)
        )
        Configuration.getInstance().userAgentValue = context.packageName
        Configuration.getInstance().osmdroidBasePath =
            File(context.filesDir, "osmdroid")
        Configuration.getInstance().osmdroidTileCache =
            File(context.cacheDir, "osmdroid")
        
        mapManager.startLocationTracking { location ->
            center.value = location
            walkViewModel.addLocation(location)
        }
    }
    
    AndroidView(
        factory = {
            MapView(context).apply {
                setTileSource(TileSourceFactory.MAPNIK)
                setMultiTouchControls(true)
                controller.setZoom(zoomLevel.doubleValue)
                controller.setCenter(center.value)
                
                val marker = Marker(this)
                marker.position = center.value
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                marker.title = "You are here"
                
                overlays.add(marker)
                
            }
        },
        update = { mapView ->
            mapView.overlays.clear()
            
            mapView.controller.setCenter(center.value)
//            mapView.controller.setZoom(zoomLevel.doubleValue)
            
            val marker = Marker(mapView)
            marker.position = center.value
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            marker.title = "You are here"
            
            val points = locations
            val polyline = Polyline().apply {
                setPoints(points)
                outlinePaint.color = android.graphics.Color.RED
                outlinePaint.strokeWidth = 10f
            }
            
            mapView.overlays.add(polyline)
            mapView.overlays.add(marker)
            mapView.invalidate()
        },
        
        modifier = modifier.fillMaxSize()
    )
}
