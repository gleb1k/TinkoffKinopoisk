package ru.glebik.feature.home.api.model.response.detail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.glebik.feature.home.api.model.response.common.Country
import ru.glebik.feature.home.api.model.response.common.Genre

@Serializable
data class FilmDetailResponse(
    @SerialName("kinopoiskId")
    val kinopoiskId: Int,
    @SerialName("imdbId")
    val imdbId: String?,
    @SerialName("nameRu")
    val nameRu: String?,
    @SerialName("nameOriginal")
    val nameOriginal: String?,
    @SerialName("posterUrl")
    val posterUrl: String?,
    @SerialName("posterUrlPreview")
    val posterUrlPreview: String?,
    @SerialName("reviewsCount")
    val reviewsCount: Int?,
    @SerialName("ratingGoodReview")
    val ratingGoodReview: Double?,
    @SerialName("ratingGoodReviewVoteCount")
    val ratingGoodReviewVoteCount: Int?,
    @SerialName("ratingKinopoisk")
    val ratingKinopoisk: Double?,
    @SerialName("ratingKinopoiskVoteCount")
    val ratingKinopoiskVoteCount: Int?,
    @SerialName("ratingImdb")
    val ratingImdb: Double?,
    @SerialName("ratingImdbVoteCount")
    val ratingImdbVoteCount: Int?,
    @SerialName("ratingFilmCritics")
    val ratingFilmCritics: Double?,
    @SerialName("ratingFilmCriticsVoteCount")
    val ratingFilmCriticsVoteCount: Int?,
    @SerialName("ratingAwait")
    val ratingAwait: Double?,
    @SerialName("ratingAwaitCount")
    val ratingAwaitCount: Int?,
    @SerialName("ratingRfCriticsVoteCount")
    val ratingRfCriticsVoteCount: Int?,
    @SerialName("webUrl")
    val webUrl: String?,
    @SerialName("year")
    val year: Int?,
    @SerialName("filmLength")
    val filmLength: Int?,
    @SerialName("description")
    val description: String?,
    @SerialName("isTicketsAvailable")
    val isTicketsAvailable: Boolean?,
    @SerialName("type")
    val type: String?,
    @SerialName("ratingMpaa")
    val ratingMpaa: String?,
    @SerialName("countries")
    val countries: List<Country>?,
    @SerialName("genres")
    val genres: List<Genre>?,
    @SerialName("serial")
    val serial: Boolean?,
    @SerialName("shortFilm")
    val shortFilm: Boolean?,
    @SerialName("completed")
    val completed: Boolean?,
    @SerialName("hasImax")
    val hasImax: Boolean?,
    @SerialName("has3D")
    val has3D: Boolean?,
    @SerialName("lastSync")
    val lastSync: String?
)