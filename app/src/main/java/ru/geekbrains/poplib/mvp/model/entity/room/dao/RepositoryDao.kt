package ru.geekbrains.poplib.mvp.model.entity.room.dao

import androidx.room.*
import ru.geekbrains.poplib.mvp.model.entity.room.RoomGithubRepository

@Dao
interface RepositoryDao {

    //region Select

    @Query("SELECT * FROM RoomGithubRepository")
    fun getAll(): List<RoomGithubRepository>

    @Query("SELECT * FROM RoomGithubRepository WHERE userLogin = :userLogin")
    fun getUserRepositories(userLogin: String): List<RoomGithubRepository>

    //endregion Select

    //region Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg repositories: RoomGithubRepository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repository: RoomGithubRepository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repositories: List<RoomGithubRepository>)

    //endregion Insert

    //region Update

    @Update
    fun update(repository: RoomGithubRepository)

    @Update
    fun update(vararg repositories: RoomGithubRepository)

    @Update
    fun update(repositories: List<RoomGithubRepository>)

    //endregion Update

    //region Delete

    @Delete
    fun delete(repository: RoomGithubRepository)

    @Delete
    fun delete(vararg repository: RoomGithubRepository)

    @Delete
    fun delete(repositories: List<RoomGithubRepository>)

    //endregion Delete


}