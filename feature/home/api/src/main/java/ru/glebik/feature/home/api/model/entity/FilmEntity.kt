package ru.glebik.feature.home.api.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films")
class FilmEntity(
    @PrimaryKey val id: Int,
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
) {
}