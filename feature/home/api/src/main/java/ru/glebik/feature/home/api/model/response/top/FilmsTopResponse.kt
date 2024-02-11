package ru.glebik.feature.home.api.model.response.top


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmsTopResponse(
    @SerialName("pagesCount")
    val pagesCount: Int,
    @SerialName("films")
    val filmsList: List<FilmResponse>
)