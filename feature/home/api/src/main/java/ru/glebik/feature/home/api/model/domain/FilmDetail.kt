package ru.glebik.feature.home.api.model.domain

data class FilmDetail(
    val id: Int,
    val nameRu: String?,
    val nameOriginal: String?,
    val year: Int?,
    val genres: List<String>?,
    val countries: List<String>?,
    val posterUrl: String?,

    val ratingKinopoisk: Double?,
    val ratingImdb: Double?,
    val webUrl: String?,
    val description: String?,

    val serial: Boolean?,
    val shortFilm: Boolean?,
    val completed: Boolean?,
    val has3D: Boolean?,

    val isFavorite: Boolean = false,
)