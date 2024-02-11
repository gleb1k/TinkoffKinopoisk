package ru.glebik.feature.home.api.usecase

import ru.glebik.core.utils.ResultWrapper
import ru.glebik.feature.home.api.model.domain.FilmSmall

interface GetFilmsTopUseCase {
    suspend operator fun invoke(): ResultWrapper<List<FilmSmall>>
}