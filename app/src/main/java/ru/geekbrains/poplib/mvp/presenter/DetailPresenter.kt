package ru.geekbrains.poplib.mvp.presenter

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.geekbrains.poplib.mvp.model.entity.GithubRepository
import ru.geekbrains.poplib.mvp.view.DetailView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class DetailPresenter(private val repository: GithubRepository)
    : MvpPresenter<DetailView>() {

    @Inject
    lateinit var router: Router

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