package ru.glebik.feature.home.api.service

import ru.glebik.feature.home.api.model.response.detail.FilmDetailResponse
import ru.glebik.feature.home.api.model.response.top.FilmsTopResponse

interface FilmsService {

    suspend fun getFilmsTop(): FilmsTopResponse

    suspend fun getFilmDetail(id: Int): FilmDetailResponse
}