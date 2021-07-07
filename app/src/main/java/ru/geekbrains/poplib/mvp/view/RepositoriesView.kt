package ru.geekbrains.poplib.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepositoriesView : MvpView {
    fun init()
    fun updateList()
    fun showError(message: String)
    fun clearError()

    fun setUserName(text: String)
    fun loadAvatar(avatarUrl: String)
    fun setUserId(sId: String)
    fun setUserLogin(login: String)
    fun setUserReposCount(publicRepos: String)
}
