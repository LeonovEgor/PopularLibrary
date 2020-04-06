package ru.geekbrains.poplib.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.poplib.mvp.model.entity.GithubUser
import ru.geekbrains.poplib.mvp.model.entity.room.RoomGithubUser
import ru.geekbrains.poplib.mvp.model.entity.room.cache.IUserCache
import ru.geekbrains.poplib.mvp.model.entity.room.db.AppDatabase

class UserCache(private val database: AppDatabase) : IUserCache {

    override fun insertOrReplace(user: GithubUser) {
        val roomUser = RoomGithubUser(
            user.login,
            user.name,
            user.id,
            user.avatarUrl,
            user.reposUrl,
            user.publicRepos
        )
        database.userDao.insert(roomUser)
    }

    override fun getUserByLogin(login: String): Single<GithubUser> =

        Single.create { emitter ->
            database.userDao.getUserByLogin(login)?.let { roomUser ->
                emitter.onSuccess(
                    GithubUser(
                        roomUser.id,
                        roomUser.login,
                        roomUser.name,
                        roomUser.avatarUrl,
                        roomUser.reposUrl,
                        roomUser.publicRepos
                    )
                )
            } ?: let {
                emitter.onError(RuntimeException("No such user in cache"))
            }
        }

}