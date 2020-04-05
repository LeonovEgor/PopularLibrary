package ru.geekbrains.poplib.mvp.model.entity.room.dao

import androidx.room.*
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.poplib.mvp.model.entity.room.RoomGithubUser

@Dao
interface UserDao {

    //region Select

    @Query("SELECT * FROM RoomGithubUser")
    fun getAll(): Single<List<RoomGithubUser>>

    @Query("SELECT * FROM RoomGithubUser WHERE RoomGithubUser.login = :login LIMIT 1")
    fun getUserByLogin(login: String): Single<RoomGithubUser>  //Можно использовать Maybe

    //endregion Select

    //region Insert

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGithubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: RoomGithubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: RoomGithubUser)

    //endregion Insert

    //region Delete

    @Delete
    fun delete(user: RoomGithubUser)

    @Delete
    fun delete(vararg user: RoomGithubUser)

    //endregion Delete

}