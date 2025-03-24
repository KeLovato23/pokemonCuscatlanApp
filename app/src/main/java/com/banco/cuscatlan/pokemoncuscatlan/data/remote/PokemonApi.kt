package com.banco.cuscatlan.pokemoncuscatlan.data.remote


import retrofit2.http.GET

data class PokemonResponse(
    val results: List<PokemonDto>
)

data class PokemonDto(
    val name: String,
    val url: String
)

interface PokemonApi {
    @GET("pokemon?limit=100")
    suspend fun getPokemonList(): PokemonResponse
}