package com.banco.cuscatlan.pokemoncuscatlan.data.remote


import com.google.gson.annotations.SerializedName

data class PokemonDetailsResponse(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<TypeSlot>,
    val stats: List<StatSlot>,
    val sprites: Sprites
)

data class TypeSlot(val slot: Int, val type: Type)
data class Type(val name: String)

data class StatSlot(
    @SerializedName("base_stat") val baseStat: Int,
    val stat: Stat
)

data class Stat(val name: String)

data class Sprites(val other: OtherSprites)
data class OtherSprites(
    @SerializedName("official-artwork") val officialArtwork: OfficialArtwork
)
data class OfficialArtwork(
    @SerializedName("front_default") val frontDefault: String
)

// species
data class PokemonSpeciesResponse(
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntry>
)

data class FlavorTextEntry(
    @SerializedName("flavor_text") val flavorText: String,
    val language: Language
)

data class Language(val name: String)