package ru.geekbrains.poplib.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepositoriesView : MvpView {
    fun init()
    fun updateList()
    fun setError(message: String)
    fun clearError()
}
