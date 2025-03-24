package com.banco.cuscatlan.pokemoncuscatlan.domain.model

data class TrainerProfile(
    val name: String,
    val hobby: String,
    val birthdate: String,
    val document: String?,
    val photoUri: String
)