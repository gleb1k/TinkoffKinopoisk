package ru.glebik.feature.home.internal.presentation.home.viewmodel

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import kotlinx.collections.immutable.PersistentList
import ru.glebik.core.utils.ResultWrapper
import ru.glebik.feature.home.api.model.domain.FilmSmall
import ru.glebik.feature.home.api.usecase.AddFilmToFavoriteUseCase
import ru.glebik.feature.home.api.usecase.GetFavoriteFilmsUseCase
import ru.glebik.feature.home.api.usecase.GetFilmsTopUseCase
import ru.glebik.feature.home.api.usecase.SearchFilmByNameUseCase

internal class HomeStoreFactory(
    private val storeFactory: StoreFactory,

    private val getFilmsTopUseCase: GetFilmsTopUseCase,
    private val addFilmToFavoriteUseCase: AddFilmToFavoriteUseCase,
    private val searchFilmByNameUseCase: SearchFilmByNameUseCase,
    private val getFavoriteFilmsUseCase: GetFavoriteFilmsUseCase,
) {
    fun create(): HomeStore = object :
        HomeStore,
        Store<HomeStore.Intent, HomeStore.State, Nothing> by storeFactory.create(
            name = HomeStore::class.simpleName,
            initialState = HomeStore.State(),
            bootstrapper = null,
            executorFactory = {
                HomeExecutor(
                    getFilmsTopUseCase,
                    addFilmToFavoriteUseCase,
                    searchFilmByNameUseCase,
                    getFavoriteFilmsUseCase
                )
            },
            reducer = HomeReducer(),
        ) {}

    sealed interface Message {

        data class SetFilmsList(
            val list: PersistentList<FilmSmall>
        ) : Message

        data class SetPageType(
            val isFavoriteType: Boolean
        ) : Message

        data class EnableSearch(
            val isSearchEnable: Boolean
        ) : Message

        data class SetQuerySearch(val query: String) : Message

        data class AddFilmToFavorite(val newList: PersistentList<FilmSmall>) : Message

        data object SetNotFound : Message
        data object SetLoading : Message
        data class SetError(val error: ResultWrapper.Failed) : Message
    }
}