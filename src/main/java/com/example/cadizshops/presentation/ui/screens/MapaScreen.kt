package com.example.cadizshops.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cadizshops.data.repository.TiendasRepository
import com.example.cadizshops.presentation.ui.components.GoogleMapComponent
import com.example.cadizshops.presentation.ui.theme.BackgroundDark
import com.example.cadizshops.presentation.ui.theme.BackgroundLight
import com.example.cadizshops.presentation.ui.theme.PrimaryTeal
import com.google.android.gms.common.GoogleApiAvailability

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapaScreen(
    navController: NavHostController,
    isDarkMode: MutableState<Boolean>,
    onMenuClick: () -> Unit
) {
    val tiendas = remember { TiendasRepository.getTiendas() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Mapa de Tiendas",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onMenuClick) {
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = "Menú",
                            tint = Color.White
                        )
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
        val backgroundColor =
            if (isDarkMode.value) BackgroundDark else BackgroundLight

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(paddingValues)
        ) {
            // Comprobamos si hay Google Play Services disponibles
            val googleApiAvailability = GoogleApiAvailability.getInstance()
            val context = androidx.compose.ui.platform.LocalContext.current
            val status = googleApiAvailability.isGooglePlayServicesAvailable(context)

            if (status == com.google.android.gms.common.ConnectionResult.SUCCESS) {
                // En dispositivos/emuladores con Google Play -> se muestra el mapa
                GoogleMapComponent(
                    tiendas = tiendas,
                    isDarkMode = isDarkMode.value
                )
            } else {
                // En tu Huawei sin Google Play -> mostramos mensaje legible en ambos temas
                Text(
                    text = "No es posible ejecutar la aplicación CadizShops sin los Servicios de Google Play, que no son compatibles con tu dispositivo.",
                    color = if (isDarkMode.value) Color.White else Color.Black,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp)
                )
            }
        }
    }
}
