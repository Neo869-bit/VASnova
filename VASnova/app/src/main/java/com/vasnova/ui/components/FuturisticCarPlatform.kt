package com.vasnova.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.vasnova.ui.theme.CharcoalCard
import com.vasnova.ui.theme.CyberEmerald
import com.vasnova.ui.theme.NeonGreen
import com.vasnova.ui.theme.Obsidian
import com.vasnova.ui.theme.PureWhite
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

private val CyanLight = Color(0xFF67E8F9)

/**
 * Cyber coupe on a grounded isometric turntable.
 * The disc stays flat; the vehicle yaws with [graphicsLayer] `rotationY`.
 */
@Composable
fun FuturisticCarPlatform(
    isCharging: Boolean,
    modifier: Modifier = Modifier,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "yaw")
    val liveRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 10_000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "vehicleYaw",
    )

    var parkedRotation by remember { mutableFloatStateOf(0f) }
    LaunchedEffect(isCharging, liveRotation) {
        if (isCharging) {
            parkedRotation = liveRotation
        }
    }

    val yawDegrees = if (isCharging) liveRotation else parkedRotation
    val density = LocalDensity.current.density
    val glowAlpha = if (isCharging) 0.55f else 0.18f

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(260.dp),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val cx = size.width / 2f
            val cy = size.height * 0.72f
            val rx = size.width * 0.38f
            val ry = rx * 0.28f

            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        CyberEmerald.copy(alpha = glowAlpha),
                        NeonGreen.copy(alpha = glowAlpha * 0.32f),
                        Color.Transparent,
                    ),
                    center = Offset(cx, cy),
                    radius = rx * 1.45f,
                ),
                radius = rx * 1.45f,
                center = Offset(cx, cy),
            )

            drawGroundedTurntable(
                cx = cx,
                cy = cy,
                rx = rx,
                ry = ry,
                charging = isCharging,
                tickYawDegrees = yawDegrees,
            )
        }

        Canvas(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-18).dp)
                .size(width = 280.dp, height = 108.dp)
                .graphicsLayer {
                    rotationY = yawDegrees
                    cameraDistance = 16f * density
                    transformOrigin = TransformOrigin.Center
                },
        ) {
            drawCyberCoupe(charging = isCharging)
        }
    }
}

private fun DrawScope.drawGroundedTurntable(
    cx: Float,
    cy: Float,
    rx: Float,
    ry: Float,
    charging: Boolean,
    tickYawDegrees: Float,
) {
    drawOval(
        brush = Brush.radialGradient(
            colors = listOf(
                CharcoalCard.copy(alpha = 0.95f),
                Obsidian.copy(alpha = 0.88f),
            ),
            center = Offset(cx, cy),
            radius = rx,
        ),
        topLeft = Offset(cx - rx, cy - ry),
        size = Size(rx * 2f, ry * 2f),
    )

    val ringAlphas = listOf(0.95f, 0.5f, 0.22f)
    val ringScales = listOf(1f, 0.78f, 0.56f)
    ringScales.forEachIndexed { index, scale ->
        drawOval(
            color = CyberEmerald.copy(alpha = ringAlphas[index] * if (charging) 1f else 0.4f),
            topLeft = Offset(cx - rx * scale, cy - ry * scale),
            size = Size(rx * 2f * scale, ry * 2f * scale),
            style = Stroke(width = if (index == 0) 3.6f else 2f),
        )
    }

    val ticks = 28
    val yawRad = tickYawDegrees * (PI.toFloat() / 180f)
    for (i in 0 until ticks) {
        val angle = (i / ticks.toFloat()) * 2f * PI.toFloat() + yawRad
        val inner = 0.58f
        val outer = 0.94f
        drawLine(
            color = NeonGreen.copy(alpha = if (charging) 0.42f else 0.16f),
            start = Offset(cx + cos(angle) * rx * inner, cy + sin(angle) * ry * inner),
            end = Offset(cx + cos(angle) * rx * outer, cy + sin(angle) * ry * outer),
            strokeWidth = 1.5f,
            cap = StrokeCap.Round,
        )
    }
}

private fun DrawScope.drawCyberCoupe(charging: Boolean) {
    val w = size.width
    val h = size.height
    val ground = h * 0.86f
    val wheelR = h * 0.175f
    val frontWheel = Offset(w * 0.24f, ground)
    val rearWheel = Offset(w * 0.76f, ground)

    val bodyFill = CharcoalCard.copy(alpha = 0.96f)
    val bodyStroke = if (charging) CyberEmerald else PureWhite.copy(alpha = 0.88f)
    val cabinStroke = NeonGreen.copy(alpha = if (charging) 0.9f else 0.45f)
    val stroke = Stroke(width = 2.4f, cap = StrokeCap.Round, join = StrokeJoin.Round)

    drawWheel(frontWheel, wheelR, charging)
    drawWheel(rearWheel, wheelR, charging)

    val body = Path().apply {
        moveTo(w * 0.05f, ground - wheelR * 0.35f)
        lineTo(w * 0.07f, ground - wheelR * 0.95f)
        cubicTo(
            w * 0.10f, ground - wheelR * 1.35f,
            w * 0.18f, ground - wheelR * 1.45f,
            w * 0.28f, ground - wheelR * 1.42f,
        )
        cubicTo(
            w * 0.36f, ground - wheelR * 1.48f,
            w * 0.40f, ground - wheelR * 1.85f,
            w * 0.46f, ground - wheelR * 2.38f,
        )
        cubicTo(
            w * 0.52f, ground - wheelR * 2.62f,
            w * 0.62f, ground - wheelR * 2.58f,
            w * 0.70f, ground - wheelR * 2.35f,
        )
        cubicTo(
            w * 0.78f, ground - wheelR * 2.05f,
            w * 0.84f, ground - wheelR * 1.70f,
            w * 0.88f, ground - wheelR * 1.55f,
        )
        lineTo(w * 0.93f, ground - wheelR * 1.62f)
        lineTo(w * 0.96f, ground - wheelR * 1.88f)
        lineTo(w * 0.985f, ground - wheelR * 1.72f)
        lineTo(w * 0.97f, ground - wheelR * 1.28f)
        lineTo(w * 0.94f, ground - wheelR * 0.72f)
        cubicTo(
            w * 0.92f, ground - wheelR * 0.28f,
            w * 0.86f, ground - wheelR * 0.22f,
            w * 0.82f, ground - wheelR * 0.28f,
        )
        lineTo(w * 0.70f, ground - wheelR * 0.28f)
        lineTo(w * 0.30f, ground - wheelR * 0.28f)
        lineTo(w * 0.18f, ground - wheelR * 0.28f)
        cubicTo(
            w * 0.12f, ground - wheelR * 0.22f,
            w * 0.08f, ground - wheelR * 0.22f,
            w * 0.05f, ground - wheelR * 0.35f,
        )
        close()
    }

    drawPath(body, color = bodyFill, style = Fill)
    drawPath(body, color = bodyStroke, style = stroke)

    val cabin = Path().apply {
        moveTo(w * 0.405f, ground - wheelR * 1.52f)
        lineTo(w * 0.47f, ground - wheelR * 2.28f)
        lineTo(w * 0.68f, ground - wheelR * 2.22f)
        lineTo(w * 0.80f, ground - wheelR * 1.62f)
        lineTo(w * 0.42f, ground - wheelR * 1.55f)
        close()
    }
    drawPath(cabin, color = Color(0xFF0E1A18).copy(alpha = 0.92f), style = Fill)
    drawPath(cabin, color = cabinStroke, style = Stroke(width = 1.8f, join = StrokeJoin.Round))

    drawLine(
        color = bodyStroke.copy(alpha = 0.7f),
        start = Offset(w * 0.12f, ground - wheelR * 1.18f),
        end = Offset(w * 0.38f, ground - wheelR * 1.32f),
        strokeWidth = 1.6f,
        cap = StrokeCap.Round,
    )
    drawLine(
        color = cabinStroke,
        start = Offset(w * 0.56f, ground - wheelR * 2.30f),
        end = Offset(w * 0.56f, ground - wheelR * 1.58f),
        strokeWidth = 1.4f,
    )

    val lightbar = Path().apply {
        addRoundRect(
            RoundRect(
                left = w * 0.055f,
                top = ground - wheelR * 1.12f,
                right = w * 0.16f,
                bottom = ground - wheelR * 0.88f,
                cornerRadius = CornerRadius(8f, 8f),
            ),
        )
    }
    drawPath(
        lightbar,
        brush = Brush.horizontalGradient(
            colors = listOf(
                CyanLight.copy(alpha = if (charging) 1f else 0.35f),
                CyberEmerald.copy(alpha = if (charging) 0.95f else 0.28f),
            ),
        ),
        style = Fill,
    )
    if (charging) {
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(CyanLight.copy(alpha = 0.55f), Color.Transparent),
                center = Offset(w * 0.10f, ground - wheelR * 1.0f),
                radius = wheelR * 1.1f,
            ),
            radius = wheelR * 1.1f,
            center = Offset(w * 0.10f, ground - wheelR * 1.0f),
        )
    }

    val diffuserTop = ground - wheelR * 0.62f
    val diffuserBottom = ground - wheelR * 0.22f
    val diffuserX = w * 0.90f
    for (i in 0..3) {
        val x = diffuserX + i * (w * 0.018f)
        drawLine(
            color = CyberEmerald.copy(alpha = if (charging) 0.85f else 0.3f),
            start = Offset(x, diffuserTop),
            end = Offset(x + w * 0.01f, diffuserBottom),
            strokeWidth = 2.2f,
            cap = StrokeCap.Round,
        )
    }

    drawLine(
        color = CyberEmerald.copy(alpha = if (charging) 0.7f else 0.22f),
        start = Offset(w * 0.88f, ground - wheelR * 0.78f),
        end = Offset(w * 0.97f, ground - wheelR * 0.78f),
        strokeWidth = 2.4f,
        cap = StrokeCap.Round,
    )
}

private fun DrawScope.drawWheel(
    center: Offset,
    radius: Float,
    charging: Boolean,
) {
    drawCircle(
        color = Obsidian,
        radius = radius,
        center = center,
    )
    drawCircle(
        color = NeonGreen.copy(alpha = if (charging) 0.95f else 0.45f),
        radius = radius,
        center = center,
        style = Stroke(width = 3.4f),
    )
    drawCircle(
        color = CharcoalCard,
        radius = radius * 0.58f,
        center = center,
    )
    drawCircle(
        color = CyberEmerald.copy(alpha = if (charging) 0.8f else 0.3f),
        radius = radius * 0.18f,
        center = center,
    )
    for (i in 0 until 5) {
        val angle = i * (2f * PI.toFloat() / 5f) - PI.toFloat() / 2f
        drawLine(
            color = NeonGreen.copy(alpha = if (charging) 0.55f else 0.22f),
            start = center,
            end = Offset(
                center.x + cos(angle) * radius * 0.5f,
                center.y + sin(angle) * radius * 0.5f,
            ),
            strokeWidth = 1.4f,
        )
    }
}
