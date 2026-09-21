package com.vasnova.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vasnova.domain.IoTRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: IoTRepository,
) : ViewModel() {

    val connectionState = repository.connectionState
    val telemetry = repository.telemetry

    private val _powerEnabled = MutableStateFlow(true)
    val powerEnabled: StateFlow<Boolean> = _powerEnabled.asStateFlow()

    fun connect() {
        viewModelScope.launch {
            repository.connect(FAKE_DEVICE_ADDRESS)
        }
    }

    fun disconnect() {
        viewModelScope.launch {
            repository.disconnect()
            _powerEnabled.value = true
        }
    }

    fun togglePower() {
        viewModelScope.launch {
            val enabled = !_powerEnabled.value
            repository.togglePower(enabled)
                .onSuccess { _powerEnabled.value = enabled }
        }
    }

    companion object {
        private const val FAKE_DEVICE_ADDRESS = "fake-esp32"
    }
}
