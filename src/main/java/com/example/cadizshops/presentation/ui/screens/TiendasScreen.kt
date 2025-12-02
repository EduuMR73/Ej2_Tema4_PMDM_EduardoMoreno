package com.example.cadizshops.presentation.ui.screens

import android.media.SoundPool
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cadizshops.R
import com.example.cadizshops.data.repository.TiendasRepository
import com.example.cadizshops.presentation.navigation.AppRoutes
import com.example.cadizshops.presentation.ui.components.TiendaCard
import com.example.cadizshops.presentation.ui.theme.BackgroundDark
import com.example.cadizshops.presentation.ui.theme.BackgroundLight
import com.example.cadizshops.presentation.ui.theme.PrimaryTeal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TiendasScreen(
    navController: NavHostController,
    isDarkMode: MutableState<Boolean>,
    onMenuClick: () -> Unit
) {
    val context = LocalContext.current

    // SoundPool configurado para 2 sonidos simultáneos
    val soundPool = remember { SoundPool.Builder().setMaxStreams(2).build() }

    // Cargar sonidos desde res/raw (asegúrate de tener cash.mp3 y explosion.mp3)
    val soundCash = remember {
        soundPool.load(context, R.raw.cash, 1)
    }
    val soundExplosion = remember {
        soundPool.load(context, R.raw.explosion, 1)
    }

    val tiendas = remember { TiendasRepository.getTiendas() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Tiendas de Cádiz",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onMenuClick) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menú",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate(AppRoutes.Mapa.route) }) {
                        Icon(
                            imageVector = Icons.Default.Map,
                            contentDescription = "Mapa",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryTeal,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = PrimaryTeal,
                contentColor = Color.White,
                modifier = Modifier.height(56.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navController.navigate(AppRoutes.Sensores.route) }) {
                        Icon(
                            imageVector = Icons.Default.Sensors,
                            contentDescription = "Sensores",
                            tint = Color.White
                        )
                    }
                    Text(
                        text = "Sensores",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isDarkMode.value) BackgroundDark else BackgroundLight)
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(tiendas) { tienda ->
                    TiendaCard(
                        tienda = tienda,
                        isDarkMode = isDarkMode.value,
                        onSwipeRight = {
                            if (soundCash != 0) {
                                soundPool.play(soundCash, 1f, 1f, 1, 0, 1f)
                            }
                        },
                        onSwipeLeft = {
                            if (soundExplosion != 0) {
                                soundPool.play(soundExplosion, 1f, 1f, 1, 0, 1f)
                            }
                        }
                    )
                }
            }
        }
    }

    // Liberar recursos cuando se destruya la pantalla
    DisposableEffect(Unit) {
        onDispose {
            soundPool.release()
        }
    }
}
