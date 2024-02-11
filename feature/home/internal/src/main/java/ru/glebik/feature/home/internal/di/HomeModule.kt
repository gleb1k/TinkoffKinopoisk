package ru.glebik.feature.home.internal.di

import cafe.adriel.voyager.core.registry.screenModule
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.glebik.core.navigation.SharedScreen
import ru.glebik.core.utils.di.CoroutineDispatchers
import ru.glebik.feature.home.api.repository.FilmsRepository
import ru.glebik.feature.home.api.service.FilmsService
import ru.glebik.feature.home.api.usecase.AddFilmToFavoriteUseCase
import ru.glebik.feature.home.api.usecase.GetFavoriteFilmsUseCase
import ru.glebik.feature.home.api.usecase.GetFilmDetailUseCase
import ru.glebik.feature.home.api.usecase.GetFilmsTopUseCase
import ru.glebik.feature.home.api.usecase.SearchFilmByNameUseCase
import ru.glebik.feature.home.internal.data.FilmsRepositoryImpl
import ru.glebik.feature.home.internal.data.FilmsServiceImpl
import ru.glebik.feature.home.internal.data.mapper.FilmDaoMapper
import ru.glebik.feature.home.internal.data.mapper.FilmDetailResponseMapper
import ru.glebik.feature.home.internal.data.mapper.FilmSmallResponseMapper
import ru.glebik.feature.home.internal.domain.AddFilmToFavoriteUseCaseImpl
import ru.glebik.feature.home.internal.domain.GetFavoriteFilmsUseCaseImpl
import ru.glebik.feature.home.internal.domain.GetFilmDetailUseCaseImpl
import ru.glebik.feature.home.internal.domain.GetFilmsTopUseCaseImpl
import ru.glebik.feature.home.internal.domain.SearchFilmByNameUseCaseImpl
import ru.glebik.feature.home.internal.presentation.detail.DetailScreen
import ru.glebik.feature.home.internal.presentation.detail.viewmodel.DetailScreenModel
import ru.glebik.feature.home.internal.presentation.detail.viewmodel.DetailStore
import ru.glebik.feature.home.internal.presentation.detail.viewmodel.DetailStoreFactory
import ru.glebik.feature.home.internal.presentation.home.HomeScreen
import ru.glebik.feature.home.internal.presentation.home.viewmodel.HomeScreenModel
import ru.glebik.feature.home.internal.presentation.home.viewmodel.HomeStore
import ru.glebik.feature.home.internal.presentation.home.viewmodel.HomeStoreFactory

val homeModule = module {
    single<FilmSmallResponseMapper> {
        FilmSmallResponseMapper()
    }

    single<FilmDetailResponseMapper> {
        FilmDetailResponseMapper()
    }

    single<FilmDaoMapper> {
        FilmDaoMapper()
    }

    single<FilmsService> {
        FilmsServiceImpl(get())
    }
    factory<FilmsRepository> {
        FilmsRepositoryImpl(
            filmsService = get(),
            db = get(),
            ioDispatcher = get(named(CoroutineDispatchers.IO)),
            filmDetailResponseMapper = get(),
            filmSmallResponseMapper = get(),
            filmDaoMapper = get()
        )
    }

    factory<HomeStore> {
        HomeStoreFactory(
            storeFactory = get(),
            getFilmsTopUseCase = get(),
            addFilmToFavoriteUseCase = get(),
            searchFilmByNameUseCase = get(),
            getFavoriteFilmsUseCase = get()
        ).create()
    }

    factory<HomeScreenModel> { HomeScreenModel(get()) }

    factory<GetFilmsTopUseCase> {
        GetFilmsTopUseCaseImpl(get())
    }

    factory<GetFavoriteFilmsUseCase> {
        GetFavoriteFilmsUseCaseImpl(get())
    }

    factory<GetFilmDetailUseCase> {
        GetFilmDetailUseCaseImpl(get())
    }

    factory<SearchFilmByNameUseCase> {
        SearchFilmByNameUseCaseImpl(get())
    }

    factory<AddFilmToFavoriteUseCase> {
        AddFilmToFavoriteUseCaseImpl(get())
    }
    //--detail
    factory<DetailStore> {
        DetailStoreFactory(
            storeFactory = get(), getFilmDetailUseCase = get()
        ).create()
    }

    factory<DetailScreenModel> { DetailScreenModel(get()) }

}

val homeScreenModule = screenModule {
    register<SharedScreen.Home> { HomeScreen }
    register<SharedScreen.Detail> { DetailScreen(it.id) }
}