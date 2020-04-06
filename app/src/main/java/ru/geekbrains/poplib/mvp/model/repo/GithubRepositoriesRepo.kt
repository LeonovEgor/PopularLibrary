package ru.geekbrains.poplib.mvp.model.repo

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.poplib.mvp.model.api.IDataSource
import ru.geekbrains.poplib.mvp.model.entity.GithubRepository
import ru.geekbrains.poplib.mvp.model.entity.GithubUser
import ru.geekbrains.poplib.mvp.model.entity.room.cache.IRepositoriesCache
import ru.geekbrains.poplib.ui.network.NetworkStatus

class GithubRepositoriesRepo(
    private val api: IDataSource,
    private val networkStatus: NetworkStatus,
    private val cache: IRepositoriesCache
) {

    fun getUserRepos(user: GithubUser): @NonNull Single<List<GithubRepository>> =
        networkStatus.isOnlineSingle()
            .flatMap { isOnline ->
                if (isOnline) {
                    api.getRepos(user.reposUrl)
                        .map { userRepos ->
                            cache.insertOrReplace(user.login, userRepos)
                            userRepos
                        }
                } else {
                    cache.getRepositories(user.login)
                }.subscribeOn(Schedulers.io())
            }
}