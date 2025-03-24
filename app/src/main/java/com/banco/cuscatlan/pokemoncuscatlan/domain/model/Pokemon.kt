package com.banco.cuscatlan.pokemoncuscatlan.domain.model

data class Pokemon(
    val name: String,
    val number: Int
) {
    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$number.png"
}