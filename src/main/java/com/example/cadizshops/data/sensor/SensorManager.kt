package com.example.cadizshops.data.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.example.cadizshops.domain.model.SensorData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DeviceSensorManager(context: Context) : SensorEventListener {
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private val _sensorData = MutableStateFlow(SensorData())
    val sensorData: StateFlow<SensorData> = _sensorData

    private val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private val gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    private val lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

    fun startListening() {
        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
        gyroscope?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
        lightSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    fun stopListening() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event ?: return

        val currentData = _sensorData.value

        when (event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> {
                _sensorData.value = currentData.copy(
                    acelerometerX = event.values[0],
                    acelerometerY = event.values[1],
                    acelerometerZ = event.values[2]
                )
            }
            Sensor.TYPE_GYROSCOPE -> {
                _sensorData.value = currentData.copy(
                    gyroscopeX = event.values[0],
                    gyroscopeY = event.values[1],
                    gyroscopeZ = event.values[2]
                )
            }
            Sensor.TYPE_LIGHT -> {
                _sensorData.value = currentData.copy(
                    lightSensor = event.values[0]
                )
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // No hacer nada
    }
}
