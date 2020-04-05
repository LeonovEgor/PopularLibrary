package ru.geekbrains.poplib.mvp.model.entity.room.cache

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.poplib.mvp.model.entity.GithubUser

interface IUserCache {
    fun insertOrReplace(user: GithubUser)
    fun getUserByLogin(login: String): Single<GithubUser>
}