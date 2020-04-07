package ru.geekbrains.poplib.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_repositories.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.geekbrains.poplib.R
import ru.geekbrains.poplib.mvp.model.api.ApiHolder
import ru.geekbrains.poplib.mvp.model.entity.room.db.AppDatabase
import ru.geekbrains.poplib.mvp.model.repo.GithubRepositoriesRepo
import ru.geekbrains.poplib.mvp.model.repo.GithubUsersRepo
import ru.geekbrains.poplib.mvp.model.repo.RepositoriesCache
import ru.geekbrains.poplib.mvp.model.repo.UserCache
import ru.geekbrains.poplib.mvp.presenter.RepositoriesPresenter
import ru.geekbrains.poplib.mvp.view.RepositoriesView
import ru.geekbrains.poplib.ui.App
import ru.geekbrains.poplib.ui.BackButtonListener
import ru.geekbrains.poplib.ui.adapter.RepositoriesRVAdapter
import ru.geekbrains.poplib.ui.image.GlideImageLoader
import ru.geekbrains.poplib.ui.network.NetworkStatus

class RepositoriesFragment : MvpAppCompatFragment(), RepositoriesView, BackButtonListener {

    companion object {
        fun newInstance() = RepositoriesFragment()
    }

    @InjectPresenter
    lateinit var presenter: RepositoriesPresenter

    private var adapter: RepositoriesRVAdapter? = null

    private val networkStatus = NetworkStatus(App.instance)

    private val imageLoader by lazy { GlideImageLoader() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        View.inflate(context, R.layout.fragment_repositories, null)

    @ProvidePresenter
    fun providePresenter() = RepositoriesPresenter(
        AndroidSchedulers.mainThread()).apply {
        App.instance.appComponent.inject(this)
    }

    override fun init() {
        rv_repos.layoutManager = LinearLayoutManager(context)
        adapter = RepositoriesRVAdapter(presenter.repositoryListPresenter)
        rv_repos.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showError(message: String) {
        tv_error.visibility = View.VISIBLE
        tv_error.text = message
    }

    override fun clearError() {
        tv_error.text = ""
        tv_error.visibility = View.GONE
    }

    override fun setUserName(text: String) {
        tv_user_name.text = text
    }

    override fun loadAvatar(avatarUrl: String) {
        imageLoader.loadInto(avatarUrl, img_user_logo)
    }

    override fun setUserId(sId: String) {
        tv_user_id.text = sId
    }

    override fun setUserLogin(login: String) {
        tv_user_login.text = login
    }

    override fun setUserReposCount(publicRepos: String) {
        tv_public_repos.text = publicRepos
    }

    override fun backClicked() = presenter.backClicked()
}