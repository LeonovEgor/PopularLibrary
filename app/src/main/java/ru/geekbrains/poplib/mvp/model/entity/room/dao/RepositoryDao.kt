package ru.geekbrains.poplib.mvp.model.entity.room.dao

import androidx.room.*
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.poplib.mvp.model.entity.room.RoomGithubRepository

@Dao
interface RepositoryDao {

    //region Select

    @Query("SELECT * FROM RoomGithubRepository")
    fun getAll(): Single<List<RoomGithubRepository>>

    @Query("SELECT * FROM RoomGithubRepository WHERE RoomGithubRepository.id = :userId")
    fun getUserRepositories(userId: Int): Single<List<RoomGithubRepository>>

    //endregion Select

    //region Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRange(vararg repositories: RoomGithubRepository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repository: RoomGithubRepository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repositories: List<RoomGithubRepository>)
    //endregion Insert

    //region Delete
    @Delete
    fun delete(repository: RoomGithubRepository)

    @Delete
    fun deleteRange(vararg repository: RoomGithubRepository)
    //endregion Delete

}