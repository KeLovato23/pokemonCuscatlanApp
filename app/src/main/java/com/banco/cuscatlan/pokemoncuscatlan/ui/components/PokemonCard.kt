package com.banco.cuscatlan.pokemoncuscatlan.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.banco.cuscatlan.pokemoncuscatlan.domain.model.Pokemon

@Composable
fun PokemonCard(
    pokemon: Pokemon,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(modifier = Modifier.padding(12.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(4.dp))

                AsyncImage(
                    model = pokemon.imageUrl,
                    contentDescription = pokemon.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = pokemon.name.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF023E8A),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }

            Text(
                text = "#${pokemon.number.toString().padStart(3, '0')}",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PokemonCardPreview() {
    PokemonCard(
        pokemon = Pokemon(
            name = "bulbasaur",
            number = 1
        ),
        onClick = {}
    )
}