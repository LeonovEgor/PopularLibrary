package ru.geekbrains.poplib.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.poplib.mvp.model.entity.GithubRepository
import ru.geekbrains.poplib.mvp.model.entity.room.RoomGithubRepository
import ru.geekbrains.poplib.mvp.model.entity.room.cache.IRepositoriesCache
import ru.geekbrains.poplib.mvp.model.entity.room.db.AppDatabase

class RepositoriesCache(private val database: AppDatabase) : IRepositoriesCache {

    override fun insertOrReplace(login: String, repositoryList: List<GithubRepository>) {
        database.repositoryDao.insert(repositoryList.map {
            RoomGithubRepository(it.id, it.name, it.forksCount, login, it.language)
        })
    }

    override fun getRepositories(login: String): Single<List<GithubRepository>> =
        Single.create { emitter ->
            database.userDao.getUserByLogin(login)?.let { roomUser ->

                emitter.onSuccess(
                    database.repositoryDao.getUserRepositories(roomUser.login).map {
                    GithubRepository(it.id, it.name, it.forksCount, it.language ?: "")
                })

            } ?: let {
                emitter.onError(RuntimeException("No such user in cache"))
            }
        }
}