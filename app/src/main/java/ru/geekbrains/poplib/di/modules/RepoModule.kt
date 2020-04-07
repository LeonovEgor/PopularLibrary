package ru.geekbrains.poplib.di.modules

import dagger.Module
import dagger.Provides
import ru.geekbrains.poplib.mvp.model.api.IDataSource
import ru.geekbrains.poplib.mvp.model.entity.room.cache.IRepositoriesCache
import ru.geekbrains.poplib.mvp.model.entity.room.cache.IUserCache
import ru.geekbrains.poplib.mvp.model.repo.GithubRepositoriesRepo
import ru.geekbrains.poplib.mvp.model.repo.GithubUsersRepo
import ru.geekbrains.poplib.ui.network.NetworkStatus
import javax.inject.Singleton

@Module(includes = [CacheModule::class, ApiModule::class])
class RepoModule {

    @Singleton
    @Provides
    fun userRepo(
        api: IDataSource,
        networkStatus: NetworkStatus,
        cache: IUserCache
    ) = GithubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun repositoriesRepo(
        api: IDataSource,
        networkStatus: NetworkStatus,
        cache: IRepositoriesCache
    ) = GithubRepositoriesRepo(api, networkStatus, cache)

}