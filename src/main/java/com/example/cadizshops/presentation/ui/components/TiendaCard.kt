package com.example.cadizshops.presentation.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cadizshops.domain.model.Tienda
import com.example.cadizshops.presentation.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun TiendaCard(
    tienda: Tienda,
    isDarkMode: Boolean,
    onSwipeRight: () -> Unit,
    onSwipeLeft: () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    val offsetX = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()

    // Animación infinita para ofertas
    val infiniteTransition = rememberInfiniteTransition(label = "Infinite")
    val scale = if (tienda.oferta) {
        infiniteTransition.animateFloat(
            initialValue = 0.98f,
            targetValue = 1.02f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = EaseInOutCubic),
                repeatMode = RepeatMode.Reverse
            ),
            label = "Scale"
        ).value
    } else {
        1f
    }

    val glow = if (tienda.oferta) {
        infiniteTransition.animateFloat(
            initialValue = 0.2f,
            targetValue = 0.8f,
            animationSpec = infiniteRepeatable(
                animation = tween(1500, easing = EaseInOutCubic),
                repeatMode = RepeatMode.Reverse
            ),
            label = "Glow"
        ).value
    } else {
        0.2f
    }

    // UpdateTransition para el modo detalle
    val transition = updateTransition(targetState = isExpanded, label = "DetailTransition")
    val elevation = transition.animateDp(label = "Elevation") { expanded ->
        if (expanded) 16.dp else 4.dp
    }
    val shadowColor = transition.animateColor(label = "ShadowColor") { expanded ->
        if (expanded) SecondaryOrange else if (isDarkMode) TextSecondaryDark else TextSecondaryLight
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .shadow(
                elevation = elevation.value,
                shape = RoundedCornerShape(12.dp),
                spotColor = shadowColor.value.copy(alpha = glow)
            )
            .clip(RoundedCornerShape(12.dp))
            .graphicsLayer {
                translationX = offsetX.value
                scaleX = scale
                scaleY = scale
            }
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onHorizontalDrag = { _, dragAmount ->
                        coroutineScope.launch {
                            offsetX.snapTo(offsetX.value + dragAmount)
                        }
                    },
                    onDragEnd = {
                        coroutineScope.launch {
                            when {
                                offsetX.value > 100 -> {
                                    offsetX.animateTo(
                                        300f,
                                        animationSpec = tween(200)
                                    )
                                    onSwipeRight()
                                    offsetX.animateTo(
                                        0f,
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioMediumBouncy,
                                            stiffness = Spring.StiffnessLow
                                        )
                                    )
                                }
                                offsetX.value < -100 -> {
                                    offsetX.animateTo(
                                        -300f,
                                        animationSpec = tween(200)
                                    )
                                    onSwipeLeft()
                                    offsetX.animateTo(
                                        0f,
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioMediumBouncy,
                                            stiffness = Spring.StiffnessLow
                                        )
                                    )
                                }
                                else -> {
                                    offsetX.animateTo(0f)
                                }
                            }
                        }
                    }
                )
            },
        colors = CardDefaults.cardColors(
            containerColor = if (isDarkMode) SurfaceDark else SurfaceLight
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = tienda.foto),
                contentDescription = tienda.nombre,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(PrimaryTeal.copy(alpha = 0.2f)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = tienda.nombre,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodyLarge,
                            color = if (isDarkMode) TextDark else TextLight
                        )
                        Text(
                            text = tienda.calle,
                            style = MaterialTheme.typography.bodySmall,
                            color = if (isDarkMode) TextSecondaryDark else TextSecondaryLight
                        )
                        Text(
                            text = tienda.productoPrincipal,
                            style = MaterialTheme.typography.bodySmall,
                            color = PrimaryTeal,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    if (tienda.oferta) {
                        Surface(
                            modifier = Modifier.clip(RoundedCornerShape(6.dp)),
                            color = SecondaryOrange
                        ) {
                            Text(
                                text = "OFERTA",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(6.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { isExpanded = !isExpanded },
                    modifier = Modifier
                        .align(Alignment.End)
                        .height(32.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryTeal
                    ),
                    contentPadding = PaddingValues(4.dp)
                ) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Color.White
                    )
                }
            }
        }

        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        if (isDarkMode) BackgroundDark else BackgroundLight
                    )
                    .padding(12.dp)
            ) {
                Divider(
                    color = if (isDarkMode) TextSecondaryDark else TextSecondaryLight,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Text(
                    text = tienda.descripcion,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (isDarkMode) TextDark else TextLight
                )
            }
        }
    }
}
