package ru.glebik.feature.home.internal.data.mapper

import ru.glebik.core.utils.mapper.ResponseMapper
import ru.glebik.feature.home.api.model.domain.FilmSmall
import ru.glebik.feature.home.api.model.response.top.FilmResponse

class FilmSmallResponseMapper : ResponseMapper<FilmResponse, FilmSmall> {

    override fun toDomain(response: FilmResponse): FilmSmall {
        with(response) {
            val genresList = genres?.map { it.genre ?: "" } ?: listOf()
            return FilmSmall(
                filmId,
                nameRu,
                nameEn,
                year,
                genresList,
                rating,
                posterUrl,
            )
        }
    }

    companion object {
        const val TAG = "FilmSmallResponseMapper"
    }
}