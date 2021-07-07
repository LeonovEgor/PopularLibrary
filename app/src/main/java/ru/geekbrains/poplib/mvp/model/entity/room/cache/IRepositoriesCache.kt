package ru.geekbrains.poplib.mvp.model.entity.room.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.poplib.mvp.model.entity.GithubRepository

interface IRepositoriesCache {
    fun insertOrReplace(login: String, repositoryList: List<GithubRepository>): Completable
    fun getRepositories(login: String): Single<List<GithubRepository>>
}