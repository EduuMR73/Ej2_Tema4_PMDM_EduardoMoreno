package com.example.cadizshops.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cadizshops.presentation.ui.theme.*

@Composable
fun SensorCard(
    title: String,
    valueX: Float,
    valueY: Float,
    valueZ: Float,
    unit: String,
    color: Color,
    isDarkMode: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clip(RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = if (isDarkMode) SurfaceDark else SurfaceLight
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Título
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = color,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Divider
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color.copy(alpha = 0.3f))
                    .padding(bottom = 12.dp)
            )

            // Valores X, Y, Z
            SensorValueRow(
                axis = "X",
                value = valueX,
                unit = unit,
                isDarkMode = isDarkMode,
                color = color
            )

            Spacer(modifier = Modifier.height(8.dp))

            SensorValueRow(
                axis = "Y",
                value = valueY,
                unit = unit,
                isDarkMode = isDarkMode,
                color = color
            )

            Spacer(modifier = Modifier.height(8.dp))

            SensorValueRow(
                axis = "Z",
                value = valueZ,
                unit = unit,
                isDarkMode = isDarkMode,
                color = color
            )
        }
    }
}

@Composable
fun SensorValueRow(
    axis: String,
    value: Float,
    unit: String,
    isDarkMode: Boolean,
    color: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = color.copy(alpha = 0.1f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Eje $axis:",
            fontWeight = FontWeight.SemiBold,
            color = if (isDarkMode) TextDark else TextLight,
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = String.format("%.2f", value),
            fontWeight = FontWeight.Bold,
            color = color,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = unit,
            color = if (isDarkMode) TextSecondaryDark else TextSecondaryLight,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun LightSensorCard(
    value: Float,
    isDarkMode: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clip(RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = if (isDarkMode) SurfaceDark else SurfaceLight
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título
            Text(
                text = "Sensor de Luz Ambiental",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFFFFC107),
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Divider
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFFFFC107).copy(alpha = 0.3f))
                    .padding(bottom = 12.dp)
            )

            // Valor grande
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .aspectRatio(1f)
                    .background(
                        color = Color(0xFFFFC107).copy(alpha = 0.1f),
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = String.format("%.0f", value),
                        style = MaterialTheme.typography.displaySmall,
                        color = Color(0xFFFFC107)
                    )
                    Text(
                        text = "lux",
                        color = if (isDarkMode) TextSecondaryDark else TextSecondaryLight,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Descripción
            Text(
                text = interpretarLuminosidad(value),
                color = if (isDarkMode) TextDark else TextLight,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

fun interpretarLuminosidad(lux: Float): String {
    return when {
        lux < 10 -> "Oscuridad total"
        lux < 50 -> "Muy oscuro"
        lux < 500 -> "Oscuro"
        lux < 10000 -> "Normal"
        lux < 50000 -> "Muy brillante"
        else -> "Luz solar directa"
    }
}
