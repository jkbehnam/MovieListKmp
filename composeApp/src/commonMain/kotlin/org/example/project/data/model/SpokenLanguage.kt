package org.example.project.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpokenLanguage(
    @SerialName("english_name")
    val englishName: String,
    /*@SerialName("iso_3166_1")
    val iso639: String,*/
    val name: String
)