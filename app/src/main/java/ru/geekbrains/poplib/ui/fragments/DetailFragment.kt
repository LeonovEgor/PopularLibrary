package ru.geekbrains.poplib.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_details.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.geekbrains.poplib.R
import ru.geekbrains.poplib.mvp.model.entity.GithubRepository
import ru.geekbrains.poplib.mvp.presenter.DetailPresenter
import ru.geekbrains.poplib.mvp.view.DetailView
import ru.geekbrains.poplib.ui.App
import ru.geekbrains.poplib.ui.BackButtonListener

class DetailFragment(private var repository: GithubRepository) : MvpAppCompatFragment(), DetailView, BackButtonListener {

    companion object {
        fun newInstance(repository: GithubRepository) = DetailFragment(repository)
    }

    @InjectPresenter
    lateinit var presenter: DetailPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        View.inflate(context, R.layout.fragment_details, null)

    @ProvidePresenter
    fun providePresenter() = DetailPresenter(repository, App.instance.getRouter())

    override fun backClicked() = presenter.backClicked()

    override fun init() {
        rep_id.text = repository.id
        rep_name.text = repository.name
        rep_fork_count.text = repository.forksCount.toString()
    }
}