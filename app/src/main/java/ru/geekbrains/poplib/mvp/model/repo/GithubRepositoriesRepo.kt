package ru.geekbrains.poplib.mvp.model.repo

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import ru.geekbrains.poplib.mvp.model.entity.GithubRepository

class GithubRepositoriesRepo {

    private val repositories = listOf(
        GithubRepository("1", "RepositoryName1", 100),
        GithubRepository("2", "RepositoryName2", 200),
        GithubRepository("3", "RepositoryName3", 300),
        GithubRepository("4", "RepositoryName4", 400)
    )

    fun getRepos(): @NonNull Observable<GithubRepository> = Observable.fromIterable(repositories)
}