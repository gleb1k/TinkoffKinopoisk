package ru.glebik.feature.home.internal.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.path
import ru.glebik.feature.home.api.model.response.detail.FilmDetailResponse
import ru.glebik.feature.home.api.model.response.top.FilmsTopResponse
import ru.glebik.feature.home.api.service.FilmsService

internal class FilmsServiceImpl(
    private val client: HttpClient
) : FilmsService {
    override suspend fun getFilmsTop(): FilmsTopResponse =
        client.get {
            url {
                path("/films/top")
                parameter(TYPE, TOP_100_POPULAR_FILMS)
            }
        }.body()

    override suspend fun getFilmDetail(id: Int): FilmDetailResponse =
        client.get {
            url {
                //Для получения описания фильма используйте get запрос /api/v2.2/films/top/<id фильма>
                //Этот путь не работает, поэтому использую из офф документации
                path("/films/${id}")
            }
        }.body()

    companion object {
        const val TYPE = "type"
        const val TOP_100_POPULAR_FILMS = "TOP_100_POPULAR_FILMS "
    }
}