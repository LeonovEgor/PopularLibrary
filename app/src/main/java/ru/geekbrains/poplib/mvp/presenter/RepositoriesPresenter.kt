package ru.geekbrains.poplib.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.geekbrains.poplib.mvp.model.entity.GithubRepository
import ru.geekbrains.poplib.mvp.model.entity.GithubUser
import ru.geekbrains.poplib.mvp.model.repo.GithubRepositoriesRepo
import ru.geekbrains.poplib.mvp.model.repo.GithubUsersRepo
import ru.geekbrains.poplib.mvp.presenter.list.IRepositoryListPresenter
import ru.geekbrains.poplib.mvp.view.RepositoriesView
import ru.geekbrains.poplib.mvp.view.list.RepositoryItemView
import ru.geekbrains.poplib.navigation.Screens
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class RepositoriesPresenter(private val mainThreadScheduler: Scheduler)
    : MvpPresenter<RepositoriesView>() {


    class RepositoryListPresenter : IRepositoryListPresenter {
        val repositories = mutableListOf<GithubRepository>()
        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null

        override fun getCount() = repositories.size

        override fun bindView(view: RepositoryItemView) {
            val repository = repositories[view.pos]
            view.setTitle(repository.name)
        }
    }

    private val userName: String = "googlesamples"
    @Inject
    private lateinit var userRepo: GithubUsersRepo
    @Inject
    private lateinit var repositoriesRepo: GithubRepositoriesRepo
    @Inject
    lateinit var router: Router

    val repositoryListPresenter = RepositoryListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadUser()

        repositoryListPresenter.itemClickListener = { itemView ->
            val repository = repositoryListPresenter.repositories[itemView.pos]

            router.navigateTo(Screens.RepositoryScreen(repository))
        }
    }

    private fun loadUser() {
        viewState.clearError()

        userRepo.getUser(userName)
            .observeOn(mainThreadScheduler)
            .subscribe({user ->
                viewState.setUserId("( ${user.id} )")
                viewState.setUserLogin(user.login)
                viewState.setUserName(user.name)
                viewState.loadAvatar(user.avatarUrl)
                viewState.setUserReposCount(user.publicRepos.toString())

                loadRepos(user)
            }, {
                Timber.e(it)
                viewState.showError(it.toString())
            })
    }

    private fun loadRepos(user: GithubUser) {
        viewState.clearError()

        repositoriesRepo.getUserRepos(user)
            .observeOn(mainThreadScheduler)
            .subscribe( {repos ->
                repositoryListPresenter.repositories.clear()
                repositoryListPresenter.repositories.addAll(repos)
                viewState.updateList()
            },
            {error ->
                Timber.e(error)
                viewState.showError(error.message.toString())
            }
        )
    }

    fun backClicked() : Boolean {
        router.exit()
        return true
    }
}