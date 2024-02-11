package ru.glebik.feature.home.api.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.glebik.feature.home.api.model.entity.FilmEntity

@Dao
interface FilmDao {

    @Query("SELECT * FROM films")
    suspend fun getAll(): List<FilmEntity>

    @Query("SELECT * FROM films WHERE id=:id")
    suspend fun byId(id: Int): FilmEntity?

    @Insert
    suspend fun insert(entity: FilmEntity): Long

}