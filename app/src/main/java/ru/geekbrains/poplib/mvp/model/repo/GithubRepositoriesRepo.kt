package ru.geekbrains.poplib.mvp.model.repo

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.poplib.mvp.model.api.IDataSource
import ru.geekbrains.poplib.mvp.model.entity.GithubRepository

class GithubRepositoriesRepo(val api: IDataSource) {

    fun getRepos(url: String): @NonNull Single<List<GithubRepository>> = api
        .getRepos(url)
        .subscribeOn(Schedulers.io())

}