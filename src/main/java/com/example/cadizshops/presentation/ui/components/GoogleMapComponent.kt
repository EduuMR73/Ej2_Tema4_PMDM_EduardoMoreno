package com.example.cadizshops.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.cadizshops.domain.model.Tienda
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun GoogleMapComponent(
    tiendas: List<Tienda>,
    isDarkMode: Boolean
) {
    // Centro aproximado de Cádiz
    val cadizCenter = remember { LatLng(36.5267, -6.2945) }

    // Cámara fija sobre Cádiz (no usamos CameraUpdateFactory para evitar el NPE)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(cadizCenter, 13f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(
            isBuildingEnabled = true,
            isMyLocationEnabled = false
        )
    ) {
        // Marcadores para cada tienda
        tiendas.forEach { tienda ->
            Marker(
                state = MarkerState(
                    position = LatLng(tienda.latitud, tienda.longitud)
                ),
                title = tienda.nombre,
                snippet = tienda.calle
            )
        }
    }
}
