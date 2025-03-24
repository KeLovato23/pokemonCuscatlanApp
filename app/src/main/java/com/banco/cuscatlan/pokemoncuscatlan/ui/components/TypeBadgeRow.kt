package com.banco.cuscatlan.pokemoncuscatlan.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.banco.cuscatlan.pokemoncuscatlan.domain.model.TypeColor

@Composable
fun TypeBadgeRow(types: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        types.forEachIndexed { index, type ->
            val colors = typeColor(type)
            Row(
                modifier = Modifier
                    .background(color = colors.background, shape = RoundedCornerShape(50))
                    .border(width = 1.dp, color = colors.border, shape = RoundedCornerShape(50))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
                    .then(if (index > 0) Modifier.padding(start = 8.dp) else Modifier),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = type.replaceFirstChar { it.uppercase() },
                    fontSize = 12.sp,
                    color = colors.text,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


private fun typeColor(type: String): TypeColor {
    return when (type.lowercase()) {
        "fire", "fuego" -> TypeColor(Color(0xFFFFE0B2), Color(0xFFFFA726), Color(0xFFE65100))
        "water", "agua" -> TypeColor(Color(0xFFB3E5FC), Color(0xFF0288D1), Color(0xFF01579B))
        "grass", "planta" -> TypeColor(Color(0xFFC8E6C9), Color(0xFF66BB6A), Color(0xFF2E7D32))
        "poison", "veneno" -> TypeColor(Color(0xFFE1BEE7), Color(0xFF9C27B0), Color(0xFF4A148C))
        else -> TypeColor(Color(0xFFE0E0E0), Color(0xFF9E9E9E), Color(0xFF424242))
    }
}

