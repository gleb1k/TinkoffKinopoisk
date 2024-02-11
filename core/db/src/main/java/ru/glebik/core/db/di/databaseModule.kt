package ru.glebik.core.db.di

import androidx.room.Room
import org.koin.dsl.module
import ru.glebik.core.db.DatabaseConstants
import ru.glebik.core.db.MainDatabase

val databaseModule = module {
    single<MainDatabase> {
        Room.databaseBuilder(
            get(),
            MainDatabase::class.java,
            DatabaseConstants.NAME
        ).build()
    }
}