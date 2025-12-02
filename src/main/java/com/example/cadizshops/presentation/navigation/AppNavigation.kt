package com.example.cadizshops.presentation.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cadizshops.presentation.ui.common.AppNavigationDrawer
import com.example.cadizshops.presentation.ui.screens.MapaScreen
import com.example.cadizshops.presentation.ui.screens.SensoresScreen
import com.example.cadizshops.presentation.ui.screens.TiendasScreen
import kotlinx.coroutines.launch

@Composable
fun AppNavigation(isDarkMode: MutableState<Boolean>) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    // Asegurar que siempre esté cerrado al entrar en la Activity / recomposición grande
    LaunchedEffect(Unit) {
        drawerState.close()
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppNavigationDrawer(
                isDarkMode = isDarkMode,
                navController = navController,
                onNavigate = {
                    coroutineScope.launch { drawerState.close() }
                }
            )
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = AppRoutes.Tiendas.route
        ) {
            composable(AppRoutes.Tiendas.route) {
                TiendasScreen(
                    navController = navController,
                    isDarkMode = isDarkMode,
                    onMenuClick = {
                        coroutineScope.launch { drawerState.open() }
                    }
                )
            }
            composable(AppRoutes.Mapa.route) {
                MapaScreen(
                    navController = navController,
                    isDarkMode = isDarkMode,
                    onMenuClick = {
                        coroutineScope.launch { drawerState.open() }
                    }
                )
            }
            composable(AppRoutes.Sensores.route) {
                SensoresScreen(
                    navController = navController,
                    isDarkMode = isDarkMode,
                    onMenuClick = {
                        coroutineScope.launch { drawerState.open() }
                    }
                )
            }
        }
    }
}
