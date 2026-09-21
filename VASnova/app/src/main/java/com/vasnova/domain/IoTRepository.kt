package com.vasnova.domain

import kotlinx.coroutines.flow.StateFlow

sealed interface ConnectionState {
    data object Disconnected : ConnectionState
    data object Connecting : ConnectionState
    data object Connected : ConnectionState
    data class Error(val message: String) : ConnectionState
}

interface IoTRepository {
    val connectionState: StateFlow<ConnectionState>
    val telemetry: StateFlow<DeviceTelemetry?>

    suspend fun connect(deviceAddress: String)
    suspend fun disconnect()
    suspend fun togglePower(enabled: Boolean): Result<Unit>
}