package ru.glebik.feature.home.internal

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import ru.glebik.core.utils.ResultWrapper
import ru.glebik.feature.home.api.model.domain.FilmSmall
import ru.glebik.feature.home.internal.data.FilmsRepositoryImpl
import ru.glebik.feature.home.internal.domain.GetFavoriteFilmsUseCaseImpl
import kotlin.test.assertEquals

class GetFavoriteFilmsUseCaseImplTest {
    @MockK
    private lateinit var repositoryImpl: FilmsRepositoryImpl

    private lateinit var useCase: GetFavoriteFilmsUseCaseImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetFavoriteFilmsUseCaseImpl(repositoryImpl)
    }

    @Test
    fun whenGetFavoriteFilmsUseCaseImplTestExpectedSuccess() {
        val expectedData: ResultWrapper.Success<List<FilmSmall>> = mockk {
        }
        coEvery {
            repositoryImpl.getFavoriteFilms()
        } returns expectedData

        runTest {
            val result = useCase() as ResultWrapper.Success

            assertEquals(expectedData, result)
        }
    }

    @Test
    fun whenGetFavoriteFilmsUseCaseImplTestExpectedFail() {
        val expectedFail: ResultWrapper.Failed = mockk()
        coEvery {
            repositoryImpl.getFavoriteFilms()
        } returns expectedFail

        runTest {
            val result = useCase()
            assertEquals(expectedFail, result)
        }
    }
}