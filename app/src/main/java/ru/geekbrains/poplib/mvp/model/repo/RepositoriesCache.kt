package ru.geekbrains.poplib.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.poplib.mvp.model.entity.room.RoomGithubRepository
import ru.geekbrains.poplib.mvp.model.entity.room.cache.IRepositoriesCache
import ru.geekbrains.poplib.mvp.model.entity.room.db.AppDatabase

class RepositoriesCache(private val database: AppDatabase) : IRepositoriesCache {

    override fun insertOrReplace(repositoryList: List<RoomGithubRepository>) =
        database.repositoryDao.insert(repositoryList)

    override fun getRepositories(login: String): Single<List<RoomGithubRepository>> =
        database.userDao.getUserByLogin(login)
            .flatMap { user ->
                database.repositoryDao.getUserRepositories(user.id)
            }
            .subscribeOn(Schedulers.io())
}