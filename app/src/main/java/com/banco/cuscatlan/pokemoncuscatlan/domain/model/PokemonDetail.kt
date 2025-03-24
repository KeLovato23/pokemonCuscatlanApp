package com.banco.cuscatlan.pokemoncuscatlan.domain.model

data class PokemonDetail(
    val name: String,
    val number: Int,
    val imageUrl: String,
    val weight: Double,     // en kg
    val height: Double,     // en metros
    val description: String,
    val types: List<String>,
    val stats: List<Pair<String, Int>> // Ej: ("HP", 50)
)