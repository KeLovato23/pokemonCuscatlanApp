package com.banco.cuscatlan.pokemoncuscatlan.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.banco.cuscatlan.pokemoncuscatlan.ui.components.PokemonCard
import com.banco.cuscatlan.pokemoncuscatlan.ui.components.SearchBar
import com.banco.cuscatlan.pokemoncuscatlan.ui.components.TopBarMain
import com.banco.cuscatlan.pokemoncuscatlan.viewmodel.PokemonViewModel

@Composable
fun MainScreen(
    viewModel: PokemonViewModel,
    onItemClick: (String) -> Unit // ← Cambiado: ahora espera el nombre del Pokémon
) {
    val query by viewModel.searchQuery.collectAsState()
    val pokemonList by viewModel.pokemonList.collectAsState()

    val filteredList = remember(query, pokemonList) {
        if (query.isEmpty()) pokemonList
        else pokemonList.filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.number.toString().contains(query)
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopBarMain()


        Text(
            text = buildAnnotatedString {
                append("¡Hola, ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("bienvenido")
                }
                append("!")
            },
            fontSize = 20.sp,
            color = Color(0xFF023E8A),
            modifier = Modifier.padding(start = 4.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        SearchBar(
            query = query,
            onQueryChange = viewModel::onSearchQueryChange
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(filteredList) { pokemon ->
                PokemonCard(
                    pokemon = pokemon,
                    onClick = { onItemClick(pokemon.name) }
                )
            }
        }
    }
}