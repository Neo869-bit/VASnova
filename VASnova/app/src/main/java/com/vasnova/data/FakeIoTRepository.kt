package com.vasnova.data

import com.vasnova.domain.ConnectionState
import com.vasnova.domain.DeviceTelemetry
import com.vasnova.domain.IoTRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

class FakeIoTRepository @Inject constructor() : IoTRepository {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _connectionState = MutableStateFlow<ConnectionState>(ConnectionState.Disconnected)
    override val connectionState: StateFlow<ConnectionState> = _connectionState.asStateFlow()

    private val _telemetry = MutableStateFlow<DeviceTelemetry?>(null)
    override val telemetry: StateFlow<DeviceTelemetry?> = _telemetry.asStateFlow()

    private var telemetryJob: Job? = null
    private var powerEnabled = true

    override suspend fun connect(deviceAddress: String) {
        withContext(Dispatchers.IO) {
            if (_connectionState.value is ConnectionState.Connected) return@withContext

            _connectionState.value = ConnectionState.Connecting
            delay(CONNECT_DELAY_MS)
            _connectionState.value = ConnectionState.Connected
            startTelemetryEmission()
        }
    }

    override suspend fun disconnect() {
        withContext(Dispatchers.IO) {
            telemetryJob?.cancel()
            telemetryJob = null
            _telemetry.value = null
            _connectionState.value = ConnectionState.Disconnected
        }
    }

    override suspend fun togglePower(enabled: Boolean): Result<Unit> {
        return withContext(Dispatchers.IO) {
            powerEnabled = enabled
            Result.success(Unit)
        }
    }

    private fun startTelemetryEmission() {
        telemetryJob?.cancel()
        telemetryJob = scope.launch {
            while (isActive) {
                _telemetry.value = DeviceTelemetry(
                    temperatureCelsius = BASE_TEMPERATURE + Random.nextFloat() * TEMPERATURE_VARIANCE,
                    batteryPercent = BASE_BATTERY,
                    inputPowerWatts = if (powerEnabled) BASE_INPUT_POWER else 0f,
                    outputPowerWatts = if (powerEnabled) BASE_OUTPUT_POWER else 0f,
                )
                delay(TELEMETRY_INTERVAL_MS)
            }
        }
    }

    companion object {
        private const val TELEMETRY_INTERVAL_MS = 1000L
        private const val CONNECT_DELAY_MS = 500L
        private const val BASE_TEMPERATURE = 24.5f
        private const val TEMPERATURE_VARIANCE = 1f
        private const val BASE_BATTERY = 85
        private const val BASE_INPUT_POWER = 45f
        private const val BASE_OUTPUT_POWER = 12f
    }
}
