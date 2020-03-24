package ru.geekbrains.poplib.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface DetailView : MvpView {
    fun init()
    fun setId(text: String)
    fun setTitle(text: String)
    fun setForksCount(text: String)
}