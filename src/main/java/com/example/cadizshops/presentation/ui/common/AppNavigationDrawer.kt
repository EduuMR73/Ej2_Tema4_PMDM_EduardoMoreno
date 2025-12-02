package com.example.cadizshops.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cadizshops.presentation.navigation.AppRoutes
import com.example.cadizshops.presentation.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigationDrawer(
    isDarkMode: MutableState<Boolean>,
    navController: NavHostController,
    onNavigate: () -> Unit
) {
    ModalDrawerSheet(
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .background(
                if (isDarkMode.value) SurfaceDark else SurfaceLight
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header
            Text(
                text = "CadizShops",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = PrimaryTeal,
                modifier = Modifier.padding(vertical = 24.dp)
            )

            Divider(color = if (isDarkMode.value) TextSecondaryDark else TextSecondaryLight)

            Spacer(modifier = Modifier.height(16.dp))

            // Menu Items
            NavigationDrawerItem(
                icon = { Icon(Icons.Default.Store, contentDescription = null) },
                label = { Text("Tiendas") },
                selected = false,
                onClick = {
                    navController.navigate(AppRoutes.Tiendas.route) {
                        popUpTo(AppRoutes.Tiendas.route) { inclusive = true }
                    }
                    onNavigate()
                }
            )

            NavigationDrawerItem(
                icon = { Icon(Icons.Default.Map, contentDescription = null) },
                label = { Text("Mapa") },
                selected = false,
                onClick = {
                    navController.navigate(AppRoutes.Mapa.route) {
                        popUpTo(AppRoutes.Mapa.route) { inclusive = true }
                    }
                    onNavigate()
                }
            )

            NavigationDrawerItem(
                icon = { Icon(Icons.Default.Sensors, contentDescription = null) },
                label = { Text("Sensores") },
                selected = false,
                onClick = {
                    navController.navigate(AppRoutes.Sensores.route) {
                        popUpTo(AppRoutes.Sensores.route) { inclusive = true }
                    }
                    onNavigate()
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            Divider(color = if (isDarkMode.value) TextSecondaryDark else TextSecondaryLight)

            // Dark Mode Toggle
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isDarkMode.value) "Modo Oscuro" else "Modo Claro",
                    color = if (isDarkMode.value) TextDark else TextLight
                )
                Switch(
                    checked = isDarkMode.value,
                    onCheckedChange = { isDarkMode.value = !isDarkMode.value }
                )
            }
        }
    }
}
