package com.example.healthmate.data

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log

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
    private val allSensors = sensorManager.getSensorList(Sensor.TYPE_ALL)
    
    fun hasStepSensor(): Boolean {
        allSensors.forEach {
            Log.d(
                TAG,
                "hasStepSensor with list: ${it.name} - ${it.type}"
            )
        }
        
        Log.d(TAG, "hasStepSensor: ${stepCounter?.name} - ${stepCounter?.type}")
        Log.d(TAG, "hasStepSensor: $stepDetector")
        
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
        Log.d(TAG, "registerListener")
        stepCounter?.let {
            Log.d(
                TAG,
                "stepCounter registerListener: ${it.name} - ${it.type} firing"
            )
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