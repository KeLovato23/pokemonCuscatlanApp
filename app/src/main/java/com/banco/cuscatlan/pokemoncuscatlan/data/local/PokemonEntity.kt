package com.banco.cuscatlan.pokemoncuscatlan.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey val name: String,
    val number: Int
)