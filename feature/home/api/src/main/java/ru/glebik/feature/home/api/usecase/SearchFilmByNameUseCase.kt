package ru.glebik.feature.home.api.usecase

import ru.glebik.feature.home.api.model.domain.FilmSmall

interface SearchFilmByNameUseCase {
    suspend operator fun invoke(name: String, isOnlyFavorite: Boolean): List<FilmSmall>
}