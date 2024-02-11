package ru.glebik.core.utils.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module


//INTERNAL
val dispatchersModule = module {
    factory<CoroutineDispatcher>(named(CoroutineDispatchers.MAIN)) { Dispatchers.Main }
    factory<CoroutineDispatcher>(named(CoroutineDispatchers.IO)) { Dispatchers.IO }
    factory<CoroutineDispatcher>(named(CoroutineDispatchers.DEFAULT)) { Dispatchers.Default }
    factory<CoroutineDispatcher>(named(CoroutineDispatchers.UNCONFINED)) { Dispatchers.Unconfined }
}

//API
enum class CoroutineDispatchers {
    MAIN, IO, UNCONFINED, DEFAULT,
}
