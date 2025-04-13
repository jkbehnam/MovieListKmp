package org.example.project.data.api.model

import kotlinx.serialization.SerialName


data class SpokenLanguage(
    @SerialName("english_name")
    val englishName: String,
    @SerialName("iso_3166_1")
    val iso639: String,
    val name: String
)