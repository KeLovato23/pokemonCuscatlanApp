package com.banco.cuscatlan.pokemoncuscatlan.ui.screens

import android.app.DatePickerDialog
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.banco.cuscatlan.pokemoncuscatlan.domain.model.TrainerProfile

import com.banco.cuscatlan.pokemoncuscatlan.ui.components.CustomTextField
import com.banco.cuscatlan.pokemoncuscatlan.ui.components.PrimaryButton

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainerSetupScreen(
    onSubmit: (TrainerProfile) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var hobby by remember { mutableStateOf("") }
    var birthdate by remember { mutableStateOf("") }
    var document by remember { mutableStateOf("") }
    var isAdult by remember { mutableStateOf(false) }
    var photoUri by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        photoUri = it
    }

    val dateFormatter = DateTimeFormatter.ofPattern("dd /MM/yyyy")
    val calendar = Calendar.getInstance()

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, day ->
                val selectedDate = LocalDate.of(year, month + 1, day)
                birthdate = selectedDate.format(dateFormatter)
                isAdult = calculateAge(birthdate) >= 18
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            datePicker.maxDate = System.currentTimeMillis()
        }
    }

    val showDui = isAdult
    val isFormValid = name.isNotBlank() && birthdate.isNotBlank() && photoUri != null &&
            (!showDui || document.matches(Regex("^\\d{8}-\\d$")))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Configura tu perfil",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF023E8A)
        )

        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
                .clickable { launcher.launch("image/*") },
            contentAlignment = Alignment.Center
        ) {
            if (photoUri != null) {
                AsyncImage(model = photoUri, contentDescription = null)
            } else {
                Icon(Icons.Default.Person, contentDescription = null, tint = Color.Gray)
            }
        }

        CustomTextField(
            value = name,
            onValueChange = { name = it },
            placeholder = "Nombre *"
        )

        CustomTextField(
            value = hobby,
            onValueChange = { hobby = it },
            placeholder = "Pasatiempo favorito"
        )

        // 🔄 Date Picker personalizado
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(50))
                .background(Color.White)
                .border(1.dp, Color.LightGray, RoundedCornerShape(50))
                .clickable { datePickerDialog.show() }
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            Text(
                text = if (birthdate.isNotBlank()) birthdate else "Fecha de nacimiento *",
                fontSize = 14.sp,
                color = if (birthdate.isNotBlank()) Color.Black else Color.Gray
            )
        }

        if (showDui) {
            CustomTextField(
                value = document,
                onValueChange = { document = formatDui(it) },
                placeholder = "DUI *",
                keyboardType = KeyboardType.Number
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        PrimaryButton(
            text = "Guardar y continuar",
            onClick = {
                if (!isFormValid) {
                    Toast.makeText(context, "Completa todos los campos requeridos", Toast.LENGTH_SHORT).show()
                    return@PrimaryButton
                }

                onSubmit(
                    TrainerProfile(
                        name = name,
                        hobby = hobby,
                        birthdate = birthdate,
                        document = if (showDui) document else null,
                        photoUri = photoUri.toString()
                    )
                )
            },
            enabled = true // ← ya no depende de isFormValid para que podamos manejarlo manualmente
        )
    }
}
fun calculateAge(dateStr: String): Int {
    return try {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val birthDate = LocalDate.parse(dateStr, formatter)
        val today = LocalDate.now()
        Period.between(birthDate, today).years
    } catch (e: Exception) {
        0
    }
}

fun formatDui(input: String): String {
    return input.filter { it.isDigit() }
        .take(9)
        .let {
            if (it.length > 8) "${it.take(8)}-${it.last()}" else it
        }
}