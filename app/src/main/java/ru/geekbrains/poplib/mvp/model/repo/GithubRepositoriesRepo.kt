package ru.geekbrains.poplib.mvp.model.repo

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.poplib.mvp.model.api.IDataSource
import ru.geekbrains.poplib.mvp.model.entity.GithubRepository
import ru.geekbrains.poplib.mvp.model.entity.room.RoomGithubRepository
import ru.geekbrains.poplib.mvp.model.entity.room.cache.IRepositoriesCache
import ru.geekbrains.poplib.ui.network.NetworkStatus

class GithubRepositoriesRepo(
    private val api: IDataSource,
    private val networkStatus: NetworkStatus,
    private val cache: IRepositoriesCache
) {

    fun getUserRepos(url: String): @NonNull Single<List<GithubRepository>> =
        networkStatus.isOnlineSingle()
            .flatMap { isOnline ->
                if (isOnline) {
                    api.getRepos(url)
                            //TODO: Убрать в insertOrReplace
                        .map { userRepos ->
                            userRepos.takeIf { it.isNotEmpty() }?.let {
                                val roomRepos = userRepos.map {
                                    RoomGithubRepository(it.id, it.name, it.forksCount)
                                }
                                cache.insertOrReplace(roomRepos)
                            }
                            userRepos
                        }
                } else {
                    //TODO: Заменить url на логин!!!
                    val login: String = "lll"
                    cache.getRepositories(login)
                        .map { roomRepositories ->
                            val repos = roomRepositories.map {
                                GithubRepository(it.id, it.name, it.forksCount)
                            }
                            repos
                        }
                }.subscribeOn(Schedulers.io())
            }
}