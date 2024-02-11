package ru.glebik.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.glebik.core.db.converter.DateConverter
import ru.glebik.core.db.converter.ListConverter
import ru.glebik.feature.home.api.dao.FilmDao
import ru.glebik.feature.home.api.model.entity.FilmEntity

@Database(
    version = DatabaseConstants.VERSION,
    entities = [
        FilmEntity::class,
    ],
    autoMigrations = [

    ]
)
@TypeConverters(
    DateConverter::class, ListConverter::class
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}
