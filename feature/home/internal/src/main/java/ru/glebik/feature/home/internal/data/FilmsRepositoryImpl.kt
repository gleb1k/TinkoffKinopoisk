package ru.glebik.feature.home.internal.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.glebik.core.db.MainDatabase
import ru.glebik.core.utils.ResultWrapper
import ru.glebik.feature.home.api.model.domain.FilmDetail
import ru.glebik.feature.home.api.model.domain.FilmSmall
import ru.glebik.feature.home.api.repository.FilmsRepository
import ru.glebik.feature.home.api.service.FilmsService
import ru.glebik.feature.home.internal.data.mapper.FilmDaoMapper
import ru.glebik.feature.home.internal.data.mapper.FilmDetailResponseMapper
import ru.glebik.feature.home.internal.data.mapper.FilmSmallResponseMapper

internal class FilmsRepositoryImpl(
    private val db: MainDatabase,

    private val filmsService: FilmsService,
    private val ioDispatcher: CoroutineDispatcher,
    private val filmDetailResponseMapper: FilmDetailResponseMapper,
    private val filmSmallResponseMapper: FilmSmallResponseMapper,
    private val filmDaoMapper: FilmDaoMapper,
) : FilmsRepository {

    private val filmDao = db.filmDao()

    override suspend fun getFilmDetail(id: Int): ResultWrapper<FilmDetail> =
        withContext(ioDispatcher) {
            runCatching {
                filmsService.getFilmDetail(id)
            }.fold(
                onSuccess = {
                    ResultWrapper.Success(filmDetailResponseMapper.toDomain(it))
                },
                onFailure = {
                    getFilmDetailFromDb(id)
                }
            )
        }

    private suspend fun getFilmDetailFromDb(id: Int): ResultWrapper<FilmDetail> =
        withContext(ioDispatcher) {
            runCatching {
                filmDao.byId(id)
            }.fold(
                onSuccess = {
                    if (it != null)
                        ResultWrapper.Success(filmDaoMapper.toDomain(it))
                    else
                        ResultWrapper.Failed()
                },
                onFailure = {
                    ResultWrapper.Failed(it, it.message)
                }
            )
        }

    override suspend fun addFilmToFavorite(id: Int): ResultWrapper<FilmDetail> =
        withContext(ioDispatcher) {
            runCatching {
                val filmNetwork = filmDetailResponseMapper.toDomain(filmsService.getFilmDetail(id))
                filmDao.insert(filmDaoMapper.toEntity(filmNetwork))
                filmNetwork
            }.fold(
                onSuccess = {
                    ResultWrapper.Success(it)
                },
                onFailure = {
                    ResultWrapper.Failed(it, it.message)
                }
            )
        }

    override suspend fun getFavoriteFilms(): ResultWrapper<List<FilmSmall>> =
        withContext(ioDispatcher) {
            runCatching {
                val localList = filmDao.getAll()

                val resultList = localList.map {
                    val detail = filmDaoMapper.toDomain(it)
                    with(detail) {
                        FilmSmall(
                            id,
                            nameRu,
                            nameOriginal,
                            year.toString(),
                            genres,
                            ratingKinopoisk.toString(),
                            posterUrl,
                            isFavorite
                        )
                    }
                }
                resultList

            }.fold(
                onSuccess = {
                    ResultWrapper.Success(it)
                },
                onFailure = {
                    ResultWrapper.Failed(it, it.message)
                }
            )
        }

    override suspend fun getFilmsTop(): ResultWrapper<List<FilmSmall>> =
        withContext(ioDispatcher) {
            runCatching {

                val networkCachedList = getFilmsTopCacheFirst()

                val localList = filmDao.getAll()
                val databaseFilmIds = localList.map { it.id }
                //Если фильм есть в бд, то isFavorite==true
                val updatedNetworkFilms = networkCachedList.map { networkFilm ->
                    val isFavorite = networkFilm.id in databaseFilmIds
                    networkFilm.copy(isFavorite = isFavorite)
                }
                updatedNetworkFilms
            }.fold(
                onSuccess = {
                    ResultWrapper.Success(it)
                },
                onFailure = { ResultWrapper.Failed(it, it.message) }
            )
        }

    private suspend fun getFilmsTopCacheFirst(): List<FilmSmall> {
        val cachedList = getFromCache()
        if (cachedList.isEmpty()) {
            val network = filmsService.getFilmsTop().filmsList.map { film ->
                filmSmallResponseMapper.toDomain(film)
            }
            cacheFilms(network)
        } else
            return cachedList
        return getFromCache()
    }

    private fun cacheFilms(films: List<FilmSmall>) {
        cachedFilms = films
    }

    private fun getFromCache(): List<FilmSmall> {
        return cachedFilms
    }

    companion object {
        var cachedFilms = listOf<FilmSmall>()
    }
}