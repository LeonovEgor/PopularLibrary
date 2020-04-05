package ru.geekbrains.poplib.mvp.model.entity.room.cache

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.poplib.mvp.model.entity.room.RoomGithubRepository

interface IRepositoriesCache {
    fun insertOrReplace(repositoryList: List<RoomGithubRepository>)
    fun getRepositories(login: String): Single<List<RoomGithubRepository>>
}