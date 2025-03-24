package com.banco.cuscatlan.pokemoncuscatlan.ui.screens
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.banco.cuscatlan.pokemoncuscatlan.domain.model.TrainerProfile
import com.banco.cuscatlan.pokemoncuscatlan.navigation.saveTrainerProfile

import com.banco.cuscatlan.pokemoncuscatlan.ui.components.CustomTextField
import com.banco.cuscatlan.pokemoncuscatlan.ui.components.PrimaryButton
import com.banco.cuscatlan.pokemoncuscatlan.R

@Composable
fun TrainerEditScreen(
    onUpdated: () -> Unit
) {
    val context = LocalContext.current
    val prefs = remember {
        context.getSharedPreferences("trainer_prefs", Context.MODE_PRIVATE)
    }

    var name by remember { mutableStateOf(prefs.getString("name", "") ?: "") }
    var hobby by remember { mutableStateOf(prefs.getString("hobby", "") ?: "") }
    var birthdate by remember { mutableStateOf(prefs.getString("birthdate", "") ?: "") }
    var document by remember { mutableStateOf(prefs.getString("document", "") ?: "") }
    var isAdult by remember { mutableStateOf(calculateAge(birthdate) >= 18) }

    val showDui = isAdult
    val isFormValid = name.isNotBlank() && birthdate.isNotBlank() &&
            (!showDui || document.matches(Regex("^\\d{8}-\\d$")))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Editar perfil",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF023E8A)
        )


        Image(
            painter = painterResource(id = R.drawable.estrenado),
            contentDescription = "Entrenador",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )

        CustomTextField(value = name, onValueChange = { name = it }, placeholder = "Nombre *")
        CustomTextField(value = hobby, onValueChange = { hobby = it }, placeholder = "Pasatiempo favorito")
        CustomTextField(
            value = birthdate,
            onValueChange = {
                birthdate = it
                isAdult = calculateAge(it) >= 18
            },
            placeholder = "Fecha de nacimiento * (dd/MM/yyyy)",
            keyboardType = KeyboardType.Number
        )

        if (showDui) {
            CustomTextField(
                value = document,
                onValueChange = { document = formatDui(it) },
                placeholder = "DUI *",
                keyboardType = KeyboardType.Number
            )
        }

        PrimaryButton(
            text = "Guardar cambios",
            onClick = {
                saveTrainerProfile(
                    context,
                    TrainerProfile(
                        name,
                        hobby,
                        birthdate,
                        if (showDui) document else null,
                        photoUri = "" // ya no se guarda
                    )
                )
                onUpdated()
            },
            enabled = isFormValid
        )
    }
}