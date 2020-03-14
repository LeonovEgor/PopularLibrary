package ru.geekbrains.poplib.mvp.presenter

import ru.geekbrains.poplib.mvp.model.CounterModel
import ru.geekbrains.poplib.mvp.view.MainView

class CounterPresenter(private val view: MainView, private val counterModel: CounterModel) {

    fun counterClick() {
        view.updateCounter( counterModel.nextValue().toString())
    }

    fun getCounter() = counterModel.getValue()

    fun changeCounter(counter: Int) {
        counterModel.setValue(counter)
        view.updateCounter(counter.toString())
    }

    fun calculate() {
        view.calculate((counterModel.getValue()*counterModel.getValue()).toString())
    }
}