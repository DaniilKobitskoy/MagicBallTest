package com.mysticism.doublegame.utils

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log

class ShakeDetector(private val listener: () -> Unit) : SensorEventListener {

    private var acceleration: Float = 0.0f
    private var previousAcceleration: Float = 0.0f
    private var totalAcceleration: Float = 0.0f
    private val shakeThreshold: Float = 7.0f
    private val alpha = 0.8f
    private var gravity: FloatArray = floatArrayOf(0.0f, 0.0f, 0.0f)
    private var linearAcceleration: FloatArray = floatArrayOf(0.0f, 0.0f, 0.0f)

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
            // Сглаживание данных акселерометра
            gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0]
            gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1]
            gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2]

            linearAcceleration[0] = event.values[0] - gravity[0]
            linearAcceleration[1] = event.values[1] - gravity[1]
            linearAcceleration[2] = event.values[2] - gravity[2]

            acceleration = Math.sqrt(
                (linearAcceleration[0] * linearAcceleration[0] +
                        linearAcceleration[1] * linearAcceleration[1] +
                        linearAcceleration[2] * linearAcceleration[2]).toDouble()
            ).toFloat()

            val delta = acceleration - previousAcceleration
            totalAcceleration += delta
            previousAcceleration = acceleration

            Log.d("ShakeDetector", "Acceleration: $acceleration, Total: $totalAcceleration, Delta: $delta")

            if (totalAcceleration > shakeThreshold) {
                listener()
                totalAcceleration = 0.0f // Сброс состояния
                Log.d("ShakeDetector", "Shake detected")
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}
