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

class DetailFragment : MvpAppCompatFragment(), DetailView, BackButtonListener {

    companion object {
        const val DETAIL_KEY = "detail_key"

        fun newInstance(repository: GithubRepository) = DetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(DETAIL_KEY, repository)
            }
        }
    }

    @InjectPresenter
    lateinit var presenter: DetailPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        View.inflate(context, R.layout.fragment_details, null)

    @ProvidePresenter
    fun providePresenter() = DetailPresenter(arguments!![DETAIL_KEY] as GithubRepository).apply {
        App.instance.appComponent.inject(this)
    }

    override fun backClicked() = presenter.backClicked()

    override fun init() {

    }

    override fun setId(text: String) {
        rep_id.text = text
    }

    override fun setTitle(text: String) {
        rep_name.text = text
    }

    override fun setForksCount(text: String) {
        rep_fork_count.text = text
    }
}