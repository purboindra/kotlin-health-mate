package com.example.healthmate.data

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import android.widget.Toast

const val TAG = "SensorManager"

class SensorManager(
    context: Context, private val onStepUpdate: (Int) -> Unit
) {
    
    var initialStep: Int? = null
    
    private val sensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    
    private val stepCounter =
        sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
    private val stepDetector =
        sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
    
    val ctx = context
    
    fun hasStepSensor(): Boolean {
        return stepCounter != null || stepDetector != null
    }
    
    private val stepListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            val steps = event?.values?.firstOrNull()?.toInt() ?: return
            if (initialStep == null) initialStep = steps
            val stepsToday = steps - (initialStep ?: steps)
            /// Pass the step count to the UI
            Log.d(TAG, "onSensorChanged: $stepsToday")
            onStepUpdate(stepsToday)
        }
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    }
    
    fun registerListener() {
        if (!hasStepSensor()) {
            Toast.makeText(
                ctx, "No step sensor found", Toast.LENGTH_SHORT
            ).show()
            return
        }
        
        stepCounter?.let {
            sensorManager.registerListener(
                stepListener,
                stepCounter,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }
    
    fun unregisterListener() {
        Log.d(TAG, "unRegisterListener")
        sensorManager.unregisterListener(stepListener)
    }
}