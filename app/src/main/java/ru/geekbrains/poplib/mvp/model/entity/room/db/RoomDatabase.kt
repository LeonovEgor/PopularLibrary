package ru.geekbrains.poplib.mvp.model.entity.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.geekbrains.poplib.mvp.model.entity.room.RoomGithubRepository
import ru.geekbrains.poplib.mvp.model.entity.room.RoomGithubUser
import ru.geekbrains.poplib.mvp.model.entity.room.dao.RepositoryDao
import ru.geekbrains.poplib.mvp.model.entity.room.dao.UserDao

@Database(
    entities = [
        RoomGithubUser::class,
        RoomGithubRepository::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        private const val DB_NAME = "database.db"

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(): AppDatabase = instance
            ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context) = instance
            ?: let {
                instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, DB_NAME
                ).build()
            }
    }
}