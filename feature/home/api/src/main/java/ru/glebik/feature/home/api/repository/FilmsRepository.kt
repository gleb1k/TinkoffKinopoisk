package ru.glebik.feature.home.api.repository

import ru.glebik.core.utils.ResultWrapper
import ru.glebik.feature.home.api.model.domain.FilmDetail
import ru.glebik.feature.home.api.model.domain.FilmSmall

interface FilmsRepository {

    suspend fun getFilmsTop(): ResultWrapper<List<FilmSmall>>

    suspend fun getFilmDetail(id: Int): ResultWrapper<FilmDetail>

    suspend fun addFilmToFavorite(id: Int): ResultWrapper<FilmDetail>

    suspend fun getFavoriteFilms(): ResultWrapper<List<FilmSmall>>
}