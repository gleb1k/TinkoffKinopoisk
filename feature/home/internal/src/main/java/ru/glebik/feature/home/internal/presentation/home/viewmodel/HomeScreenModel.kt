package ru.glebik.feature.home.internal.presentation.home.viewmodel

import ru.glebik.core.presentation.MviScreenModel


class HomeScreenModel(
    private val store: HomeStore,
) : MviScreenModel<HomeStore.Intent, HomeStore.State, Nothing>(
    store
) {

    init {
        loadFilmsTop()
    }

    fun loadFilmsTop() = store.accept(HomeStore.Intent.LoadTopFilms)

    fun setPageType(isFavoriteType: Boolean) {
        store.accept(HomeStore.Intent.SetPageType(isFavoriteType))
//        store.accept(HomeStore.Intent.Search(state.value.querySearch, isFavoriteType))
    }

    fun enableSearch(isSearchEnable: Boolean) =
        store.accept(HomeStore.Intent.EnableSearch(isSearchEnable))

    fun onQuerySearchChange(query: String) =
        store.accept(HomeStore.Intent.OnQuerySearchChange(query))

    fun onSearchClick(query: String) {
        store.accept(HomeStore.Intent.Search(query = query, state.value.isFavoriteType))
    }

    fun onAddToFavoriteClick(id: Int) {
        store.accept(HomeStore.Intent.AddFilmToFavorite(id))
    }

}
