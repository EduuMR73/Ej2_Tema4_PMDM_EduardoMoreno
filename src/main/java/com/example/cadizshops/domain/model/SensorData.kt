package com.example.cadizshops.domain.model

data class SensorData(
    val acelerometerX: Float = 0f,
    val acelerometerY: Float = 0f,
    val acelerometerZ: Float = 0f,
    val gyroscopeX: Float = 0f,
    val gyroscopeY: Float = 0f,
    val gyroscopeZ: Float = 0f,
    val lightSensor: Float = 0f
)
