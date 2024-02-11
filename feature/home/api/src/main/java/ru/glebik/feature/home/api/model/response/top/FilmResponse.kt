package ru.glebik.feature.home.api.model.response.top


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.glebik.feature.home.api.model.response.common.Country
import ru.glebik.feature.home.api.model.response.common.Genre

@Serializable
data class FilmResponse(
    @SerialName("filmId")
    val filmId: Int,
    @SerialName("nameRu")
    val nameRu: String?,
    @SerialName("nameEn")
    val nameEn: String?,
    @SerialName("year")
    val year: String?,
    @SerialName("filmLength")
    val filmLength: String?,
    @SerialName("countries")
    val countries: List<Country>?,
    @SerialName("genres")
    val genres: List<Genre>?,
    @SerialName("rating")
    val rating: String?,
    @SerialName("ratingVoteCount")
    val ratingVoteCount: Int?,
    @SerialName("posterUrl")
    val posterUrl: String?,
    @SerialName("posterUrlPreview")
    val posterUrlPreview: String?,
    @SerialName("isAfisha")
    val isAfisha: Int?
)