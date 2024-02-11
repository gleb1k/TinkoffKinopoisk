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
import ru.glebik.feature.home.internal.domain.GetFilmsTopUseCaseImpl
import kotlin.test.assertEquals

class GetFilmsTopUseCaseImplTest {
    @MockK
    private lateinit var repositoryImpl: FilmsRepositoryImpl

    private lateinit var useCase: GetFilmsTopUseCaseImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetFilmsTopUseCaseImpl(repositoryImpl)
    }

    @Test
    fun whenGetFilmsTopUseCaseImplTestExpectedSuccess() {
        val expectedData: ResultWrapper.Success<List<FilmSmall>> = mockk {
        }
        coEvery {
            repositoryImpl.getFilmsTop()
        } returns expectedData

        runTest {
            val result = useCase() as ResultWrapper.Success

            assertEquals(expectedData, result)
        }
    }

    @Test
    fun whenGetFilmsTopUseCaseImplTestExpectedFail() {
        val expectedFail: ResultWrapper.Failed = mockk()
        coEvery {
            repositoryImpl.getFilmsTop()
        } returns expectedFail

        runTest {
            val result = useCase()
            assertEquals(expectedFail, result)
        }
    }
}