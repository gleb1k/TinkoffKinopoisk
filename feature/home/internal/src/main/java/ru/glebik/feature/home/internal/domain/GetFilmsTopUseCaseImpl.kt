package ru.glebik.feature.home.internal.domain

import ru.glebik.core.utils.ResultWrapper
import ru.glebik.feature.home.api.model.domain.FilmSmall
import ru.glebik.feature.home.api.repository.FilmsRepository
import ru.glebik.feature.home.api.usecase.GetFilmsTopUseCase

class GetFilmsTopUseCaseImpl(
    private val repository: FilmsRepository,
) : GetFilmsTopUseCase {
    override suspend fun invoke(): ResultWrapper<List<FilmSmall>> {
        return repository.getFilmsTop()
    }
}