package ru.glebik.feature.home.internal.data.mapper

import ru.glebik.core.utils.mapper.DaoMapper
import ru.glebik.feature.home.api.model.domain.FilmDetail
import ru.glebik.feature.home.api.model.entity.FilmEntity

class FilmDaoMapper : DaoMapper<FilmEntity, FilmDetail> {
    override fun toEntity(domain: FilmDetail): FilmEntity {
        with(domain) {
            return FilmEntity(
                id = id,
                nameRu = nameRu,
                nameOriginal = nameOriginal,
                year = year,
                genres = genres,
                countries = countries,
                posterUrl = posterUrl,
                ratingKinopoisk = ratingKinopoisk,
                ratingImdb = ratingImdb,
                webUrl = webUrl,
                description = description,
                serial = serial,
                shortFilm = shortFilm,
                completed = completed,
                has3D = has3D,
            )
        }
    }

    override fun toDomain(entity: FilmEntity): FilmDetail {
        with(entity) {
            return FilmDetail(
                id = id,
                nameRu = nameRu,
                nameOriginal = nameOriginal,
                year = year,
                genres = genres,
                countries = countries,
                posterUrl = posterUrl,
                ratingKinopoisk = ratingKinopoisk,
                ratingImdb = ratingImdb,
                webUrl = webUrl,
                description = description,
                serial = serial,
                shortFilm = shortFilm,
                completed = completed,
                has3D = has3D,

                isFavorite = true,
            )
        }
    }

    companion object {
        const val TAG = "FilmDaoMapper"
    }
}
