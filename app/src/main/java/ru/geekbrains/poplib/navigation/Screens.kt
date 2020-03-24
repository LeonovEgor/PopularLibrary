package ru.geekbrains.poplib.navigation

import ru.geekbrains.poplib.mvp.model.entity.GithubRepository
import ru.geekbrains.poplib.ui.fragments.RepositoriesFragment
import ru.geekbrains.poplib.ui.fragments.DetailFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class RepositoriesScreen() : SupportAppScreen() {
        override fun getFragment() = RepositoriesFragment.newInstance()
    }

    class RepositoryScreen(private val repository: GithubRepository) : SupportAppScreen() {
        override fun getFragment() = DetailFragment.newInstance(repository)
    }
}