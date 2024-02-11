package ru.glebik.feature.home.internal

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import ru.glebik.core.utils.ResultWrapper
import ru.glebik.feature.home.api.model.domain.FilmDetail
import ru.glebik.feature.home.internal.data.FilmsRepositoryImpl
import ru.glebik.feature.home.internal.domain.GetFilmDetailUseCaseImpl
import kotlin.test.assertEquals

class GetFilmDetailUseCaseImplTest {
    @MockK
    private lateinit var repositoryImpl: FilmsRepositoryImpl

    private lateinit var useCase: GetFilmDetailUseCaseImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetFilmDetailUseCaseImpl(repositoryImpl)
    }

    @Test
    fun whenGetFilmDetailUseCaseImplTestExpectedSuccess() {
        val requestId = 1
        val expectedData: ResultWrapper.Success<FilmDetail> = mockk {
            every { data.id } returns requestId
        }
        coEvery {
            repositoryImpl.getFilmDetail(requestId)
        } returns expectedData

        runTest {
            val result = useCase(requestId) as ResultWrapper.Success

            assertEquals(expectedData, result)
            assertEquals(requestId, result.data.id)
        }
    }

    @Test
    fun whenGetFilmDetailUseCaseImplTestExpectedFail() {
        val requestId = -1
        val expectedFail: ResultWrapper.Failed = mockk()
        coEvery {
            repositoryImpl.getFilmDetail(requestId)
        } returns expectedFail

        runTest {
            val result = useCase(requestId)
            assertEquals(expectedFail, result)
        }
    }
}