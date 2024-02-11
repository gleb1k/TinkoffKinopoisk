package ru.glebik.feature.home.api.usecase

import ru.glebik.core.utils.ResultWrapper
import ru.glebik.feature.home.api.model.domain.FilmDetail

interface AddFilmToFavoriteUseCase {
    suspend operator fun invoke(id: Int): ResultWrapper<FilmDetail>
}