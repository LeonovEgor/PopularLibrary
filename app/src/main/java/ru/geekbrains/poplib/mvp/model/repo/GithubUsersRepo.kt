package ru.geekbrains.poplib.mvp.model.repo

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.poplib.mvp.model.api.IDataSource
import ru.geekbrains.poplib.mvp.model.entity.GithubUser
import ru.geekbrains.poplib.mvp.model.entity.room.cache.IUserCache
import ru.geekbrains.poplib.ui.network.NetworkStatus

class GithubUsersRepo(
    private val api: IDataSource,
    private val networkStatus: NetworkStatus,
    private val cache: IUserCache
) {

    fun getUser(login: String): @NonNull Single<GithubUser> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUser(login)
                    .map { user ->
                        cache.insertOrReplace(user)
                        user
                    }
            } else {
                cache.getUserByLogin(login)
            }
        }.subscribeOn(Schedulers.io())
}