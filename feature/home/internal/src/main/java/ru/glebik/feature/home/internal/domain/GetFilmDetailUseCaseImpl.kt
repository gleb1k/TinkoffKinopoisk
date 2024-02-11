package ru.glebik.feature.home.internal.domain

import ru.glebik.core.utils.ResultWrapper
import ru.glebik.feature.home.api.model.domain.FilmDetail
import ru.glebik.feature.home.api.repository.FilmsRepository
import ru.glebik.feature.home.api.usecase.GetFilmDetailUseCase

class GetFilmDetailUseCaseImpl(
    private val repository: FilmsRepository,
) : GetFilmDetailUseCase {
    override suspend fun invoke(id: Int): ResultWrapper<FilmDetail> {
        return repository.getFilmDetail(id)
    }
}