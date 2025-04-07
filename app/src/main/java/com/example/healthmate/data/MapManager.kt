package com.example.healthmate.data

import android.content.Context
import android.os.Looper
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import org.osmdroid.util.GeoPoint

class MapManager(private val context: Context) {
    private val TAG = "MapManager"
    
    private var locationCallback: LocationCallback? = null
    
    fun startLocationTracking(onLocationUpdate: (GeoPoint) -> Unit) {
        val fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)
        
        val request =
            LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 2000)
                .build()
        
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                val loc = result.lastLocation ?: return
                val geoPoint = GeoPoint(loc.latitude, loc.longitude)
//                Log.d("LocationTracker", "Live location: $geoPoint")
                onLocationUpdate(geoPoint)
            }
        }
        
        try {
            fusedLocationProviderClient.requestLocationUpdates(
                request,
                locationCallback!!,
                Looper.getMainLooper()
            )
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }
}