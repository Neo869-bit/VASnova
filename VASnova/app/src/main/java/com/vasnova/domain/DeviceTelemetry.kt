package com.vasnova.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Clean domain model for ESP32 telemetry consumed by the UI.
 */
data class DeviceTelemetry(
    val temperatureCelsius: Float,
    val batteryPercent: Int,
    val inputPowerWatts: Float,
    val outputPowerWatts: Float,
    val timestamp: Long = System.currentTimeMillis()
)

/**
 * Data Transfer Object for MQTT JSON payloads received over Wi-Fi.
 */
@Serializable
data class Esp32MqttPayload(
    @SerialName("temp") val temp: Float,
    @SerialName("batt") val battery: Int,
    @SerialName("p_in") val powerIn: Float,
    @SerialName("p_out") val powerOut: Float
)