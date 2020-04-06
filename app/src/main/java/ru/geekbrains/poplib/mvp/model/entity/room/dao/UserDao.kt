package ru.geekbrains.poplib.mvp.model.entity.room.dao

import androidx.room.*
import ru.geekbrains.poplib.mvp.model.entity.room.RoomGithubUser

@Dao
interface UserDao {

    //region Select

    @Query("SELECT * FROM RoomGithubUser")
    fun getAll(): List<RoomGithubUser>

    @Query("SELECT * FROM RoomGithubUser WHERE login = :login LIMIT 1")
    fun getUserByLogin(login: String): RoomGithubUser?

    //endregion Select

    //region Insert

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGithubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomGithubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomGithubUser>)

    //endregion Insert

    //region Update

    @Update
    fun update(user: RoomGithubUser)

    @Update
    fun update(vararg user: RoomGithubUser)

    @Update
    fun update(users: List<RoomGithubUser>)

    //endregion Update

    //region Delete

    @Delete
    fun delete(user: RoomGithubUser)

    @Delete
    fun delete(vararg user: RoomGithubUser)

    @Delete
    fun delete(users: List<RoomGithubUser>)

    //endregion Delete

}