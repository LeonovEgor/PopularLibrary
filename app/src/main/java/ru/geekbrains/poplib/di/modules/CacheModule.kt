package ru.geekbrains.poplib.di.modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.poplib.mvp.model.entity.room.MIGRATION_1_2
import ru.geekbrains.poplib.mvp.model.entity.room.db.AppDatabase
import ru.geekbrains.poplib.mvp.model.repo.RepositoriesCache
import ru.geekbrains.poplib.mvp.model.repo.UserCache
import ru.geekbrains.poplib.ui.App
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        AppDatabase.DB_NAME )
        .addMigrations(MIGRATION_1_2)
        .build()

    @Provides
    fun userCache(database: AppDatabase) = UserCache(database)

    @Provides
    fun repositoriesCache(database: AppDatabase) = RepositoriesCache(database)

}