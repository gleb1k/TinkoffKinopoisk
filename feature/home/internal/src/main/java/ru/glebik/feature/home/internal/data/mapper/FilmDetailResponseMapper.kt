package ru.glebik.feature.home.internal.data.mapper

import ru.glebik.core.utils.mapper.ResponseMapper
import ru.glebik.feature.home.api.model.domain.FilmDetail
import ru.glebik.feature.home.api.model.response.detail.FilmDetailResponse

class FilmDetailResponseMapper : ResponseMapper<FilmDetailResponse, FilmDetail> {

    override fun toDomain(response: FilmDetailResponse): FilmDetail {
        with(response) {
            val genresList = genres?.map { it.genre ?: "" } ?: listOf()
            val countryList = countries?.map { it.country ?: "" } ?: listOf()
            return FilmDetail(
                kinopoiskId,
                nameRu,
                nameOriginal,
                year,
                genresList,
                countryList,
                posterUrl,

                ratingKinopoisk,
                ratingImdb,
                webUrl,
                description,
                serial,
                shortFilm,
                completed,
                has3D
            )
        }
    }

    companion object {
        const val TAG = "FilmDetailResponseMapper"
    }
}