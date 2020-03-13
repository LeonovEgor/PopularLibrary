package ru.geekbrains.poplib.mvp.presenter

import ru.geekbrains.poplib.mvp.model.CounterModel
import ru.geekbrains.poplib.mvp.view.MainView

class CounterPresenter(private val view: MainView, private val counterModel: CounterModel) {

    fun counterClick() {
        view.updateCounter( counterModel.nextValue().toString())
    }

    fun updateCounter() {
        view.updateCounter(counterModel.getValue().toString())
    }

    fun calculate() {
        view.calculate((counterModel.getValue()*counterModel.getValue()).toString())
    }
}