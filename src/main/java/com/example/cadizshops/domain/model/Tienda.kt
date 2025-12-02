package com.example.cadizshops.domain.model

import androidx.annotation.DrawableRes

data class Tienda(
    val id: Int,
    val nombre: String,
    val calle: String,
    val productoPrincipal: String,
    val latitud: Double,
    val longitud: Double,
    val descripcion: String,
    val oferta: Boolean,
    @DrawableRes val foto: Int
)
