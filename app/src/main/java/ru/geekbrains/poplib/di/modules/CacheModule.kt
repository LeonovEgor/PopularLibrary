package ru.geekbrains.poplib.di.modules

import dagger.Module
import dagger.Provides
import ru.geekbrains.poplib.mvp.model.entity.room.cache.IRepositoriesCache
import ru.geekbrains.poplib.mvp.model.entity.room.cache.IUserCache
import ru.geekbrains.poplib.mvp.model.entity.room.db.AppDatabase
import ru.geekbrains.poplib.mvp.model.repo.RepositoriesCache
import ru.geekbrains.poplib.mvp.model.repo.UserCache

@Module(
    includes = [
        DataBaseModule::class
    ]
)
class CacheModule {

    @Provides
    fun userCache(database: AppDatabase): IUserCache {
        return UserCache(database)
    }

    @Provides
    fun repositoriesCache(database: AppDatabase): IRepositoriesCache {
        return RepositoriesCache(database)
    }

}