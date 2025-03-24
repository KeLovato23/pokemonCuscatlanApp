package com.banco.cuscatlan.pokemoncuscatlan.data.repository

import com.banco.cuscatlan.pokemoncuscatlan.data.local.PokemonDao
import com.banco.cuscatlan.pokemoncuscatlan.data.local.PokemonEntity
import com.banco.cuscatlan.pokemoncuscatlan.data.remote.PokemonApi
import com.banco.cuscatlan.pokemoncuscatlan.domain.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRepository(
    private val api: PokemonApi,
    private val dao: PokemonDao
) {
    suspend fun getPokemonList(): List<Pokemon> = withContext(Dispatchers.IO) {
        try {
            val response = api.getPokemonList()
            val pokemonList = response.results.map {
                val number = extractId(it.url)
                Pokemon(name = it.name, number = number)
            }
            dao.clear()
            dao.insertAll(pokemonList.map { it.toEntity() })
            pokemonList
        } catch (e: Exception) {
            // Si falla la red, usar la base local
            dao.getAll().map { it.toPokemon() }
        }
    }

    private fun extractId(url: String): Int {
        return url.trimEnd('/').split("/").last().toInt()
    }

    private fun Pokemon.toEntity() = PokemonEntity(name = name, number = number)
    private fun PokemonEntity.toPokemon() = Pokemon(name = name, number = number)
}