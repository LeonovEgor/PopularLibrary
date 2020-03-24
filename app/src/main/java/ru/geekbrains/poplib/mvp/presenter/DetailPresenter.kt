package ru.geekbrains.poplib.mvp.presenter

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.geekbrains.poplib.mvp.model.entity.GithubRepository
import ru.geekbrains.poplib.mvp.view.DetailView
import ru.geekbrains.poplib.navigation.Screens
import ru.terrakok.cicerone.Router

@InjectViewState
class DetailPresenter(private val repository: GithubRepository, private val router: Router)
    : MvpPresenter<DetailView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setId(repository.id)
        viewState.setTitle(repository.name)
        viewState.setForksCount(repository.forksCount.toString())
    }

    fun backClicked() : Boolean {
        router.exit()
        return true
    }
}