package org.example.project.data.api.model

import kotlinx.serialization.SerialName


data class ProductionCompany(
    val id: Int,
    @SerialName("logo_path")
    val logoPath: Any,
    val name: String,
    @SerialName("origin_country")
    val originCountry: String
)