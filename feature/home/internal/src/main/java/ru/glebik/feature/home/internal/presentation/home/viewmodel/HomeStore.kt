package ru.glebik.feature.home.internal.presentation.home.viewmodel

import com.arkivanov.mvikotlin.core.store.Store
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import ru.glebik.feature.home.api.model.domain.FilmSmall

interface HomeStore : Store<HomeStore.Intent, HomeStore.State, Nothing> {

    data class State internal constructor(
        val topFilms: PersistentList<FilmSmall> = persistentListOf(),
        val isFavoriteType: Boolean = false,
        val isSearchEnable: Boolean = false,

        val querySearch: String = "",

        val isNotFound: Boolean = false,
        val isLoading: Boolean = false,
        val isError: Boolean = false,
    )

    sealed interface Intent {
        data object LoadTopFilms : Intent
        data object LoadFavoriteFilms : Intent
        data class SetPageType(val isFavoriteType: Boolean) : Intent
        data class EnableSearch(val isSearchEnable: Boolean) : Intent

        data class OnQuerySearchChange(val query: String) : Intent
        data class Search(val query: String, val isOnlyFavorite: Boolean) : Intent

        data class AddFilmToFavorite(val id: Int) : Intent
    }
}


