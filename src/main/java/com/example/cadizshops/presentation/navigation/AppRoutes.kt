package com.example.cadizshops.presentation.navigation

sealed class AppRoutes(val route: String) {
    data object Tiendas : AppRoutes("tiendas")
    data object Mapa : AppRoutes("mapa")
    data object Sensores : AppRoutes("sensores")
}
