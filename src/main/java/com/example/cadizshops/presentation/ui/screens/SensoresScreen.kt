package com.example.cadizshops.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cadizshops.data.sensor.DeviceSensorManager
import com.example.cadizshops.presentation.ui.components.LightSensorCard
import com.example.cadizshops.presentation.ui.components.SensorCard
import com.example.cadizshops.presentation.ui.theme.BackgroundDark
import com.example.cadizshops.presentation.ui.theme.BackgroundLight
import com.example.cadizshops.presentation.ui.theme.PrimaryTeal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SensoresScreen(
    navController: NavHostController,
    isDarkMode: MutableState<Boolean>,
    onMenuClick: () -> Unit
) {
    val context = LocalContext.current
    // Gestor de sensores que vive mientras la pantalla esté visible
    val sensorManager = remember { DeviceSensorManager(context) }
    val sensorData by sensorManager.sensorData.collectAsState()

    // Empezar a escuchar sensores al entrar en la pantalla
    LaunchedEffect(Unit) {
        sensorManager.startListening()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Sensores del Dispositivo",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onMenuClick) {
                        Icon(Icons.Default.Menu, contentDescription = "Menú")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryTeal,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isDarkMode.value) BackgroundDark else BackgroundLight)
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item {
                    SensorCard(
                        title = "Acelerómetro",
                        valueX = sensorData.acelerometerX,
                        valueY = sensorData.acelerometerY,
                        valueZ = sensorData.acelerometerZ,
                        unit = "m/s²",
                        color = Color(0xFF00BCD4),
                        isDarkMode = isDarkMode.value
                    )
                }

                item {
                    SensorCard(
                        title = "Giroscopio",
                        valueX = sensorData.gyroscopeX,
                        valueY = sensorData.gyroscopeY,
                        valueZ = sensorData.gyroscopeZ,
                        unit = "rad/s",
                        color = Color(0xFF7E57C2),
                        isDarkMode = isDarkMode.value
                    )
                }

                item {
                    LightSensorCard(
                        value = sensorData.lightSensor,
                        isDarkMode = isDarkMode.value
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }

    // Parar los sensores al salir de la pantalla
    DisposableEffect(Unit) {
        onDispose {
            sensorManager.stopListening()
        }
    }
}
