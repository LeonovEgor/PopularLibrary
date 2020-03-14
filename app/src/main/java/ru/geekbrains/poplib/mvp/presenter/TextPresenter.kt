package ru.geekbrains.poplib.mvp.presenter

import ru.geekbrains.poplib.mvp.model.TextModel
import ru.geekbrains.poplib.mvp.view.MainView

class TextPresenter(private val view: MainView, private val textModel: TextModel) {

    fun changeText(text: String) {
        textModel.someText = text
        view.updateText(text)
    }

    fun getText() = textModel.someText
}
