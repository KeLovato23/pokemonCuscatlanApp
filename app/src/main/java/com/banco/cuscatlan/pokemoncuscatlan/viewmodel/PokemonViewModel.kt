package com.banco.cuscatlan.pokemoncuscatlan.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.banco.cuscatlan.pokemoncuscatlan.domain.model.Pokemon
import com.banco.cuscatlan.pokemoncuscatlan.data.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonViewModel(private val repository: PokemonRepository) : ViewModel() {

    private val _pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonList: StateFlow<List<Pokemon>> = _pokemonList

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        loadPokemon()
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    private fun loadPokemon() {
        viewModelScope.launch {
            _pokemonList.value = repository.getPokemonList()
        }
    }
}