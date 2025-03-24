package com.banco.cuscatlan.pokemoncuscatlan.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.banco.cuscatlan.pokemoncuscatlan.domain.model.PokemonDetail
import com.banco.cuscatlan.pokemoncuscatlan.ui.components.PokemonImageCard
import com.banco.cuscatlan.pokemoncuscatlan.ui.components.StatRow
import com.banco.cuscatlan.pokemoncuscatlan.ui.components.TopBarDetail
import com.banco.cuscatlan.pokemoncuscatlan.ui.components.WeightHeightCard

@Composable
fun DetailScreen(
    pokemon: PokemonDetail,
    onBack: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBarDetail(name = pokemon.name, number = pokemon.number, onBack = onBack)

        Column(modifier = Modifier.padding(20.dp)) {



            PokemonImageCard(imageUrl = pokemon.imageUrl, types = pokemon.types)
            Spacer(modifier = Modifier.height(24.dp))

            WeightHeightCard(weight = pokemon.weight, height = pokemon.height)

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = pokemon.description,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
                color = Color(0xFF9E9E9E)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Estadísticas",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF023E8A)
            )
            Spacer(modifier = Modifier.height(16.dp))

            pokemon.stats.forEach { (label, value) ->
                StatRow(
                    label = label,
                    value = value,
                    type = pokemon.types.firstOrNull() ?: "default"
                )
            }
        }
    }
}