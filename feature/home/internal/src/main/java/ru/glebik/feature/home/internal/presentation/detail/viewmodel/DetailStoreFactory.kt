package ru.glebik.feature.home.internal.presentation.detail.viewmodel

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import ru.glebik.core.utils.ResultWrapper
import ru.glebik.feature.home.api.model.domain.FilmDetail
import ru.glebik.feature.home.api.usecase.GetFilmDetailUseCase

internal class DetailStoreFactory(
    private val storeFactory: StoreFactory,
    private val getFilmDetailUseCase: GetFilmDetailUseCase,
) {
    fun create(): DetailStore = object :
        DetailStore,
        Store<DetailStore.Intent, DetailStore.State, Nothing> by storeFactory.create(
            name = DetailStore::class.simpleName,
            initialState = DetailStore.State(),
            bootstrapper = null,
            executorFactory = {
                DetailExecutor(
                    getFilmDetailUseCase
                )
            },
            reducer = DetailReducer(),
        ) {}

    sealed interface Message {
        data object SetLoading : Message
        data class SetFilm(
            val film: FilmDetail
        ) : Message

        data class SetError(val error: ResultWrapper.Failed) : Message
    }
}