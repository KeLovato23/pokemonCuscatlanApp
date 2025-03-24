package com.banco.cuscatlan.pokemoncuscatlan.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.ui.text.font.FontWeight

@Composable
fun StatRow(
    label: String,
    value: Int,
    type: String = "default",
    modifier: Modifier = Modifier
) {
    val barColor = typeCardColor(type)
    val backgroundBar = barColor.copy(alpha = 0.3f)

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label.replaceFirstChar { it.uppercase() },
            modifier = Modifier.width(140.dp),
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .height(20.dp)
                .background(backgroundBar, shape = RoundedCornerShape(8.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth((value.coerceIn(0, 100) / 100f))
                    .background(barColor, shape = RoundedCornerShape(8.dp))
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = value.toString().padStart(3, '0'),
            fontSize = 14.sp,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }

    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
 fun typeCardColor(type: String?): Color {
    return when (type?.lowercase()) {
        "fire", "fuego" -> Color(0xFFFFA726)
        "water", "agua" -> Color(0xFF4FC3F7)
        "grass", "planta" -> Color(0xFF81C784)
        "electric", "eléctrico" -> Color(0xFFFFEB3B)
        "poison", "veneno" -> Color(0xFFBA68C8)
        "psychic", "psíquico" -> Color(0xFFF06292)
        "ice", "hielo" -> Color(0xFF80DEEA)
        "dragon", "dragón" -> Color(0xFF9575CD)
        "dark", "oscuro" -> Color(0xFF8D6E63)
        "fairy", "hada" -> Color(0xFFF8BBD0)
        else -> Color(0xFFE0E0E0)
    }
}

@Preview(showBackground = true)
@Composable
fun StatRowPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        StatRow(label = "HP", value = 39, type = "planta")
        StatRow(label = "Ataque", value = 52, type = "fuego")
        StatRow(label = "Defensa", value = 43, type = "veneno")
        StatRow(label = "Ataque especial", value = 60, type = "agua")
        StatRow(label = "Defensa especial", value = 50, type = "psíquico")
        StatRow(label = "Velocidad", value = 65, type = "eléctrico")
    }
}