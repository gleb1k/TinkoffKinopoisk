package ru.glebik.feature.home.internal.presentation.detail.viewmodel

import com.arkivanov.mvikotlin.core.store.Reducer

internal class DetailReducer : Reducer<DetailStore.State, DetailStoreFactory.Message> {

    override fun DetailStore.State.reduce(
        msg: DetailStoreFactory.Message,
    ) = when (msg) {
        is DetailStoreFactory.Message.SetError -> copy(
            isError = true,
            isLoading = false,
        )

        is DetailStoreFactory.Message.SetLoading -> copy(
            isLoading = true,
            isError = false,
        )

        is DetailStoreFactory.Message.SetFilm -> copy(
            isLoading = false,
            film = msg.film
        )
    }
}