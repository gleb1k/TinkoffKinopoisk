package ru.glebik.feature.home.internal.presentation.home.viewmodel

import com.arkivanov.mvikotlin.core.store.Reducer

internal class HomeReducer : Reducer<HomeStore.State, HomeStoreFactory.Message> {

    override fun HomeStore.State.reduce(
        msg: HomeStoreFactory.Message,
    ) = when (msg) {
        is HomeStoreFactory.Message.SetError -> copy(
            isError = true,
            isLoading = false,
            isNotFound = false,
        )

        is HomeStoreFactory.Message.SetLoading -> copy(
            isLoading = true,
            isError = false,
            isNotFound = false,
        )

        is HomeStoreFactory.Message.SetFilmsList -> copy(
            isNotFound = false,
            isLoading = false,
            isError = false,
            topFilms = msg.list
        )

        is HomeStoreFactory.Message.SetPageType -> copy(
            isFavoriteType = msg.isFavoriteType
        )

        is HomeStoreFactory.Message.EnableSearch -> copy(
            isSearchEnable = msg.isSearchEnable
        )

        is HomeStoreFactory.Message.SetQuerySearch -> copy(
            querySearch = msg.query
        )

        is HomeStoreFactory.Message.AddFilmToFavorite -> copy(
            topFilms = msg.newList
        )

        HomeStoreFactory.Message.SetNotFound -> copy(
            isNotFound = true,
            isLoading = false,
            isError = false,
        )
    }
}