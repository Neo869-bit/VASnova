package com.vasnova.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vasnova.domain.ConnectionState
import com.vasnova.domain.DeviceTelemetry
import com.vasnova.ui.components.FuturisticCarPlatform
import com.vasnova.ui.theme.CharcoalCard
import com.vasnova.ui.theme.CyberEmerald
import com.vasnova.ui.theme.NeonGreen
import com.vasnova.ui.theme.PureWhite
import com.vasnova.ui.theme.SilverMuted
import com.vasnova.ui.theme.SlateBorder
import com.vasnova.ui.theme.VasNovaTheme

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel(),
) {
    val connectionState by viewModel.connectionState.collectAsStateWithLifecycle()
    val telemetry by viewModel.telemetry.collectAsStateWithLifecycle()
    val powerEnabled by viewModel.powerEnabled.collectAsStateWithLifecycle()

    DashboardContent(
        connectionState = connectionState,
        telemetry = telemetry,
        powerEnabled = powerEnabled,
        onConnect = viewModel::connect,
        onDisconnect = viewModel::disconnect,
        onTogglePower = viewModel::togglePower,
    )
}

@Composable
private fun DashboardContent(
    connectionState: ConnectionState,
    telemetry: DeviceTelemetry?,
    powerEnabled: Boolean,
    onConnect: () -> Unit,
    onDisconnect: () -> Unit,
    onTogglePower: () -> Unit,
) {
    val isConnected = connectionState is ConnectionState.Connected
    val isPowerOn = powerEnabled
    val isCharging = isConnected && isPowerOn

    val metrics = listOf(
        MetricItem("TEMP", telemetry?.temperatureCelsius?.let { "%.1f".format(it) } ?: "—", "°C"),
        MetricItem("BATTERY", telemetry?.batteryPercent?.let { "$it" } ?: "—", "%"),
        MetricItem("INPUT", telemetry?.inputPowerWatts?.let { "%.0f".format(it) } ?: "—", "W"),
        MetricItem("OUTPUT", telemetry?.outputPowerWatts?.let { "%.0f".format(it) } ?: "—", "W"),
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            HeaderRow(connectionState = connectionState)

            FuturisticCarPlatform(
                isCharging = isCharging,
                modifier = Modifier.fillMaxWidth(),
            )

            Text(
                text = if (isCharging) "CHARGING SEQUENCE ACTIVE" else "PEDESTAL LOCKED",
                style = MaterialTheme.typography.labelLarge,
                color = if (isCharging) CyberEmerald else SilverMuted,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    MetricCard(metrics[0], Modifier.weight(1f), isLive = isConnected)
                    MetricCard(metrics[1], Modifier.weight(1f), isLive = isConnected)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    MetricCard(metrics[2], Modifier.weight(1f), isLive = isConnected)
                    MetricCard(metrics[3], Modifier.weight(1f), isLive = isConnected)
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                if (isConnected || connectionState is ConnectionState.Connecting) {
                    NeonSwitchButton(
                        label = "DISCONNECT",
                        filled = false,
                        enabled = true,
                        onClick = onDisconnect,
                        modifier = Modifier.weight(1f),
                    )
                } else {
                    NeonSwitchButton(
                        label = if (connectionState is ConnectionState.Connecting) "LINKING…" else "CONNECT",
                        filled = true,
                        enabled = connectionState !is ConnectionState.Connecting,
                        onClick = onConnect,
                        modifier = Modifier.weight(1f),
                    )
                }

                NeonSwitchButton(
                    label = if (powerEnabled) "POWER OFF" else "POWER ON",
                    filled = powerEnabled && isConnected,
                    enabled = isConnected,
                    onClick = onTogglePower,
                    modifier = Modifier.weight(1f),
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
private fun HeaderRow(connectionState: ConnectionState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(
                text = "VASNOVA",
                style = MaterialTheme.typography.headlineLarge,
                color = PureWhite,
            )
            Text(
                text = "EV TELEMETRY CORE",
                style = MaterialTheme.typography.labelLarge,
                color = SilverMuted,
            )
        }
        ConnectionStatusBadge(connectionState = connectionState)
    }
}

@Composable
private fun ConnectionStatusBadge(connectionState: ConnectionState) {
    val (label, glow) = when (connectionState) {
        ConnectionState.Disconnected -> "OFFLINE" to SilverMuted
        ConnectionState.Connecting -> "SYNCING" to Color(0xFFFFD166)
        ConnectionState.Connected -> "LINKED" to CyberEmerald
        is ConnectionState.Error -> "FAULT" to Color(0xFFFF4D6D)
    }

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(CharcoalCard.copy(alpha = 0.92f))
            .border(1.dp, glow.copy(alpha = 0.65f), RoundedCornerShape(20.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(glow, CircleShape),
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = glow,
            fontSize = 10.sp,
        )
    }
}

@Composable
private fun MetricCard(
    metric: MetricItem,
    modifier: Modifier = Modifier,
    isLive: Boolean,
) {
    val border = if (isLive) CyberEmerald.copy(alpha = 0.45f) else SlateBorder.copy(alpha = 0.7f)
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .background(CharcoalCard.copy(alpha = 0.88f))
            .border(1.dp, border, RoundedCornerShape(18.dp))
            .drawBehind {
                if (isLive) {
                    drawRoundRect(
                        color = CyberEmerald.copy(alpha = 0.08f),
                        cornerRadius = CornerRadius(18.dp.toPx()),
                    )
                }
            }
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = metric.label,
            style = MaterialTheme.typography.labelLarge,
            color = SilverMuted,
        )
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = metric.value,
                color = PureWhite,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.4.sp,
            )
            Text(
                text = metric.unit,
                color = SilverMuted,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 4.dp),
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            CyberEmerald.copy(alpha = if (isLive) 0.9f else 0.15f),
                            Color.Transparent,
                        ),
                    ),
                    RoundedCornerShape(2.dp),
                ),
        )
    }
}

@Composable
private fun NeonSwitchButton(
    label: String,
    filled: Boolean,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val haptics = LocalHapticFeedback.current
    val shape = RoundedCornerShape(16.dp)
    val glow = if (enabled) CyberEmerald else SlateBorder
    val background = when {
        !enabled -> CharcoalCard.copy(alpha = 0.45f)
        filled -> NeonGreen.copy(alpha = 0.22f)
        else -> CharcoalCard.copy(alpha = 0.9f)
    }

    Box(
        modifier = modifier
            .height(56.dp)
            .scale(if (pressed && enabled) 0.97f else 1f)
            .clip(shape)
            .background(background)
            .border(1.5.dp, glow.copy(alpha = if (enabled) 0.95f else 0.35f), shape)
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                    onClick()
                },
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = label,
            color = if (enabled) PureWhite else SilverMuted,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 1.8.sp,
            fontSize = 12.sp,
        )
    }
}

private data class MetricItem(
    val label: String,
    val value: String,
    val unit: String,
)

@Preview(showBackground = true, backgroundColor = 0xFF0B0F0E)
@Composable
private fun DashboardContentPreview() {
    VasNovaTheme {
        DashboardContent(
            connectionState = ConnectionState.Connected,
            telemetry = DeviceTelemetry(
                temperatureCelsius = 25.2f,
                batteryPercent = 85,
                inputPowerWatts = 45f,
                outputPowerWatts = 12f,
            ),
            powerEnabled = true,
            onConnect = {},
            onDisconnect = {},
            onTogglePower = {},
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0B0F0E)
@Composable
private fun DashboardContentDisconnectedPreview() {
    VasNovaTheme {
        DashboardContent(
            connectionState = ConnectionState.Disconnected,
            telemetry = null,
            powerEnabled = true,
            onConnect = {},
            onDisconnect = {},
            onTogglePower = {},
        )
    }
}
