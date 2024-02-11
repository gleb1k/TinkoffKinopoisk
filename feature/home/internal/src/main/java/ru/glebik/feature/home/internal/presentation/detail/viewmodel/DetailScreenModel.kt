package ru.glebik.feature.home.internal.presentation.detail.viewmodel

import ru.glebik.core.presentation.MviScreenModel


class DetailScreenModel(
    private val store: DetailStore,
) : MviScreenModel<DetailStore.Intent, DetailStore.State, Nothing>(
    store
) {
    fun load(id: Int) = store.accept(DetailStore.Intent.Load(id))
}
