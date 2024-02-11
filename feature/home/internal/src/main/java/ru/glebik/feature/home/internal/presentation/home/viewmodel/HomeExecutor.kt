package ru.glebik.feature.home.internal.presentation.home.viewmodel

import android.util.Log
import kotlinx.collections.immutable.toPersistentList
import ru.glebik.core.presentation.BaseExecutor
import ru.glebik.core.utils.ResultWrapper
import ru.glebik.feature.home.api.usecase.AddFilmToFavoriteUseCase
import ru.glebik.feature.home.api.usecase.GetFavoriteFilmsUseCase
import ru.glebik.feature.home.api.usecase.GetFilmsTopUseCase
import ru.glebik.feature.home.api.usecase.SearchFilmByNameUseCase

internal class HomeExecutor(
    private val getFilmsTopUseCase: GetFilmsTopUseCase,
    private val addFilmToFavoriteUseCase: AddFilmToFavoriteUseCase,
    private val searchFilmByNameUseCase: SearchFilmByNameUseCase,
    private val getFavoriteFilmsUseCase: GetFavoriteFilmsUseCase,
) : BaseExecutor<HomeStore.Intent, Nothing, HomeStore.State, HomeStoreFactory.Message, Nothing>() {

    override suspend fun suspendExecuteIntent(
        intent: HomeStore.Intent,
        getState: () -> HomeStore.State,
    ) = when (intent) {
        is HomeStore.Intent.LoadTopFilms -> loadTopFilms()
        is HomeStore.Intent.SetPageType -> changePageType(intent.isFavoriteType)
        is HomeStore.Intent.EnableSearch -> dispatch(HomeStoreFactory.Message.EnableSearch(intent.isSearchEnable))
        is HomeStore.Intent.OnQuerySearchChange -> dispatch(
            HomeStoreFactory.Message.SetQuerySearch(
                intent.query
            )
        )

        is HomeStore.Intent.Search -> search(intent.query, intent.isOnlyFavorite)
        is HomeStore.Intent.AddFilmToFavorite -> addToFavorite(intent.id)
        HomeStore.Intent.LoadFavoriteFilms -> loadFavoriteFilms()
    }

    private suspend fun loadTopFilms() {
        dispatch(HomeStoreFactory.Message.SetLoading)
        val response =
            getFilmsTopUseCase()
        when (response) {
            is ResultWrapper.Success -> {
                dispatch(
                    HomeStoreFactory.Message.SetFilmsList(response.data.toPersistentList())
                )
            }

            is ResultWrapper.Failed -> {
                //Если ошибка, то пробуем грузить из бд
                //ЖИЖА, ЛУЧШЕ ПЕРЕНЕСТИ ЭТУ ЛОГИКУ В РЕПОЗИТОРИЙ
                //СДЕЛАТЬ ЧТО-ТО ТИПО LOCAL_FIRST метода, но мне лень)
                when (val localResponse = getFavoriteFilmsUseCase()) {
                    is ResultWrapper.Success -> {
                        dispatch(
                            HomeStoreFactory.Message.SetFilmsList(localResponse.data.toPersistentList())
                        )
                    }

                    is ResultWrapper.Failed -> dispatch(
                        HomeStoreFactory.Message.SetError(
                            ResultWrapper.Failed(
                                response.exception,
                                response.errorMessage
                            )
                        )
                    )
                }
            }
        }
    }

    private suspend fun changePageType(isFavorite: Boolean) {
        dispatch(HomeStoreFactory.Message.SetPageType(isFavorite))

        val response = if (isFavorite)
            getFavoriteFilmsUseCase()
        else
            getFilmsTopUseCase()
        when (response) {
            is ResultWrapper.Success -> {
                dispatch(
                    HomeStoreFactory.Message.SetFilmsList(response.data.toPersistentList())
                )
            }

            is ResultWrapper.Failed -> dispatch(
                HomeStoreFactory.Message.SetError(
                    ResultWrapper.Failed(
                        response.exception,
                        response.errorMessage
                    )
                )
            )
        }
    }

    private suspend fun loadFavoriteFilms() {
        dispatch(HomeStoreFactory.Message.SetLoading)
        val response =
            getFavoriteFilmsUseCase()
        when (response) {
            is ResultWrapper.Success -> {
                dispatch(
                    HomeStoreFactory.Message.SetFilmsList(response.data.toPersistentList())
                )
            }

            is ResultWrapper.Failed -> dispatch(
                HomeStoreFactory.Message.SetError(
                    ResultWrapper.Failed(
                        response.exception,
                        response.errorMessage
                    )
                )
            )
        }
    }

    private suspend fun addToFavorite(id: Int) {
        dispatch(HomeStoreFactory.Message.SetLoading)

        addFilmToFavoriteUseCase(id)
        when (val response = getFilmsTopUseCase()) {
            is ResultWrapper.Success -> {
                dispatch(
                    HomeStoreFactory.Message.SetFilmsList(response.data.toPersistentList())
                )
            }

            is ResultWrapper.Failed -> dispatch(
                HomeStoreFactory.Message.SetError(
                    ResultWrapper.Failed(
                        response.exception,
                        response.errorMessage
                    )
                )
            )
        }
    }

    private suspend fun search(query: String, isOnlyFavorite: Boolean) {
        dispatch(HomeStoreFactory.Message.SetLoading)
        val searchResult = searchFilmByNameUseCase(query, isOnlyFavorite)

        Log.v("HomeExecutor Search", searchResult.toString())
        if (searchResult.isEmpty())
            dispatch(HomeStoreFactory.Message.SetNotFound)
        else
            dispatch(HomeStoreFactory.Message.SetFilmsList(searchResult.toPersistentList()))
    }
}
