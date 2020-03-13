package ru.geekbrains.poplib.mvp.model

class CounterModel {

    private var counter: Int = 0

    fun getValue() = counter

    fun nextValue() = ++counter

    fun setValue(value: Int) {
        counter = value
    }
}