package com.mysticism.doublegame.utils

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log

class ShakeDetector(private val listener: () -> Unit) : SensorEventListener {

    private var lastAcceleration: Float = 0.0f
    private var currentAcceleration: Float = 0.0f
    private var shakeThreshold: Float = 12.0f
    private val alpha = 0.8f
    private val gravity = FloatArray(3)
    private var isInitialized = false

    fun start(context: Context) {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        if (accelerometer == null) {
            Log.e("ShakeDetector", "Accelerometer not available")
            return
        }
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI)
    }

    fun stop(context: Context) {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0]
            gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1]
            gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2]

            val linearAcceleration = floatArrayOf(
                event.values[0] - gravity[0],
                event.values[1] - gravity[1],
                event.values[2] - gravity[2]
            )

            currentAcceleration = Math.sqrt(
                (linearAcceleration[0] * linearAcceleration[0] +
                        linearAcceleration[1] * linearAcceleration[1] +
                        linearAcceleration[2] * linearAcceleration[2]).toDouble()
            ).toFloat()

            val delta = currentAcceleration - lastAcceleration
            if (isInitialized && delta > shakeThreshold) {
                listener()
                isInitialized = false
            }

            lastAcceleration = currentAcceleration

            if (!isInitialized) {
                isInitialized = true
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}
