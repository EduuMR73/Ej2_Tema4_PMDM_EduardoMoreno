package com.example.cadizshops.data.repository

import com.example.cadizshops.R
import com.example.cadizshops.domain.model.Tienda

object TiendasRepository {
    fun getTiendas(): List<Tienda> = listOf(
        Tienda(
            id = 1,
            nombre = "El Corte Inglés",
            calle = "Calle Ancha, 30",
            productoPrincipal = "Moda y electrónica",
            latitud = 36.5321,
            longitud = -6.2954,
            descripcion = "Gran almacén con amplia selección de ropa, electrónica, hogar y accesorios. Reconocido por su calidad y variedad.",
            oferta = true,
            foto = R.drawable.image1
        ),
        Tienda(
            id = 2,
            nombre = "Zara Home",
            calle = "Plaza de Mina, 12",
            productoPrincipal = "Decoración del hogar",
            latitud = 36.5309,
            longitud = -6.2943,
            descripcion = "Tienda especializada en decoración, textiles y artículos para el hogar con diseño moderno y funcional.",
            oferta = false,
            foto = R.drawable.image2
        ),
        Tienda(
            id = 3,
            nombre = "El rastro de Gades",
            calle = "Calle Sillerería, 8",
            productoPrincipal = "Libros y papelería",
            latitud = 36.5334,
            longitud = -6.2980,
            descripcion = "Tienda tradicional con amplio surtido de libros, cómics, revistas y material de papelería para todas las edades.",
            oferta = true,
            foto = R.drawable.image3
        ),
        Tienda(
            id = 4,
            nombre = "Adidas Store",
            calle = "Avenida Campo del Sur, 15",
            productoPrincipal = "Ropa deportiva",
            latitud = 36.5285,
            longitud = -6.3012,
            descripcion = "Tienda oficial Adidas con toda la colección de ropa, calzado y accesorios deportivos de última temporada.",
            oferta = true,
            foto = R.drawable.image4
        ),
        Tienda(
            id = 5,
            nombre = "El Rastro",
            calle = "Calle Feduchy, 20",
            productoPrincipal = "Antigedades y vintage",
            latitud = 36.5298,
            longitud = -6.2924,
            descripcion = "Tienda con selección de antigüedades, objetos vintage, artículos de segunda mano y curiosidades únicas.",
            oferta = false,
            foto = R.drawable.image5
        ),
        Tienda(
            id = 6,
            nombre = "Carrefour Express",
            calle = "Calle Columela, 5",
            productoPrincipal = "Supermercado",
            latitud = 36.5342,
            longitud = -6.2967,
            descripcion = "Supermercado con productos frescos, bebidas, snacks y artículos de conveniencia de marcas conocidas.",
            oferta = true,
            foto = R.drawable.image6
        ),
        Tienda(
            id = 7,
            nombre = "Tech Store Cádiz",
            calle = "Plaza de España, 3",
            productoPrincipal = "Electrónica y accesorios",
            latitud = 36.5275,
            longitud = -6.2975,
            descripcion = "Especializada en tecnología: smartphones, laptops, tablets y accesorios de las mejores marcas.",
            oferta = false,
            foto = R.drawable.image7
        ),
        Tienda(
            id = 8,
            nombre = "Farmacia Central",
            calle = "Calle Moreno de Mora, 10",
            productoPrincipal = "Farmacia y parafarmacia",
            latitud = 36.5315,
            longitud = -6.2885,
            descripcion = "Farmacia con completa gama de medicamentos, cosmética, vitaminas y artículos de cuidado personal.",
            oferta = true,
            foto = R.drawable.image8
        )
    )
}
