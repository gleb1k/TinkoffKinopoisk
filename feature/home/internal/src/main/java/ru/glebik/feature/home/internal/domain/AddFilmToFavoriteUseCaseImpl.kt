package ru.glebik.feature.home.internal.domain

import ru.glebik.core.utils.ResultWrapper
import ru.glebik.feature.home.api.model.domain.FilmDetail
import ru.glebik.feature.home.api.repository.FilmsRepository
import ru.glebik.feature.home.api.usecase.AddFilmToFavoriteUseCase

class AddFilmToFavoriteUseCaseImpl(
    private val repository: FilmsRepository,
) : AddFilmToFavoriteUseCase {
    override suspend fun invoke(id: Int): ResultWrapper<FilmDetail> {
        return repository.addFilmToFavorite(id)
    }
}