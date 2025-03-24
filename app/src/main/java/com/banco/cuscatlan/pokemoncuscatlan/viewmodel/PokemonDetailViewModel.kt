package com.banco.cuscatlan.pokemoncuscatlan.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.banco.cuscatlan.pokemoncuscatlan.domain.model.PokemonDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.banco.cuscatlan.pokemoncuscatlan.data.remote.PokemonDetailsApi
class PokemonDetailViewModel(
    private val name: String
) : ViewModel() {

    private val _pokemonDetail = MutableStateFlow<PokemonDetail?>(null)
    val pokemonDetail: StateFlow<PokemonDetail?> = _pokemonDetail

    init {
        loadDetail()
    }

    private fun loadDetail() {
        viewModelScope.launch {
            try {
                val pokemon = PokemonDetailsApi.api.getPokemonDetail(name)
                val species = PokemonDetailsApi.api.getPokemonSpecies(name)

                val detail = PokemonDetail(
                    name = pokemon.name,
                    number = pokemon.id,
                    imageUrl = pokemon.sprites.other.officialArtwork.frontDefault,
                    weight = pokemon.weight / 10.0,
                    height = pokemon.height / 10.0,
                    description = species.flavorTextEntries.firstOrNull {
                        it.language.name == "es"
                    }?.flavorText
                        ?.replace("\n", " ")
                        ?.replace("\u000C", " ") ?: "",
                    types = pokemon.types.map { it.type.name },
                    stats = pokemon.stats.map {
                        it.stat.name.replace("-", " ") to it.baseStat
                    }
                )

                _pokemonDetail.value = detail
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
