package org.example.project.data.api.model

import kotlinx.serialization.SerialName


data class ProductionCountry(
    @SerialName("iso_3166_1")
    val iso3166: String,
    val name: String
)