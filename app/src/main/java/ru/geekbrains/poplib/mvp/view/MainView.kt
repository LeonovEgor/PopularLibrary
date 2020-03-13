package ru.geekbrains.poplib.mvp.view

interface MainView {
    fun updateCounter(text: String)
    fun updateText(text: String)
    fun calculate(text: String)
}