package com.banco.cuscatlan.pokemoncuscatlan.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.text.font.FontWeight
import com.banco.cuscatlan.pokemoncuscatlan.R

@Composable
fun WeightHeightCard(weight: Double, height: Double) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5), RoundedCornerShape(16.dp))
            .padding(vertical = 16.dp, horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Peso
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_weight),
                contentDescription = "Peso",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    text = String.format("%.1f kg", weight),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color(0xFF023E8A)
                )
                Text(
                    text = "Peso",
                    fontSize = 12.sp,
                    color = Color(0xFF2196F3)
                )
            }
        }


        Box(
            modifier = Modifier
                .width(1.dp)
                .height(40.dp)
                .background(Color.LightGray)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_height),
                contentDescription = "Altura",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    text = String.format("%.1f m", height),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color(0xFF023E8A)
                )
                Text(
                    text = "Altura",
                    fontSize = 12.sp,
                    color = Color(0xFF2196F3)
                )
            }
        }
    }
}