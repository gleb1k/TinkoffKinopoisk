package ru.glebik.feature.home.api.model.domain

data class FilmSmall(
    val id: Int,
    val nameRu: String?,
    val nameEn: String?,
    val year: String?,
    val genres: List<String>?,
    val rating: String?,
    val posterUrl: String?,

    val isFavorite: Boolean = false,
)