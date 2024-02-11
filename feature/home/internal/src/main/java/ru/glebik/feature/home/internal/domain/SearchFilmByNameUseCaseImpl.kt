package ru.glebik.feature.home.internal.domain

import ru.glebik.core.utils.ResultWrapper
import ru.glebik.feature.home.api.model.domain.FilmSmall
import ru.glebik.feature.home.api.repository.FilmsRepository
import ru.glebik.feature.home.api.usecase.SearchFilmByNameUseCase

class SearchFilmByNameUseCaseImpl(
    private val repository: FilmsRepository,
) : SearchFilmByNameUseCase {
    override suspend fun invoke(name: String, isOnlyFavorite: Boolean): List<FilmSmall> =
        when (val response =
            if (isOnlyFavorite) repository.getFavoriteFilms() else repository.getFilmsTop()) {
            is ResultWrapper.Failed -> listOf()
            is ResultWrapper.Success -> {
                if (name != "") {
                    val filteredFilms =
                        response.data.filter {
                            it.nameRu?.contains(name, ignoreCase = true) ?: false
                        }
                    filteredFilms
                } else
                    response.data
            }
        }
}