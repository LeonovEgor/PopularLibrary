package ru.geekbrains.poplib.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_main.*
import ru.geekbrains.poplib.R
import ru.geekbrains.poplib.mvp.model.CounterModel
import ru.geekbrains.poplib.mvp.model.TextModel
import ru.geekbrains.poplib.mvp.presenter.CounterPresenter
import ru.geekbrains.poplib.mvp.presenter.TextPresenter
import ru.geekbrains.poplib.mvp.view.MainView


class MainActivity : AppCompatActivity(), MainView {

    private lateinit var etSomeText: TextInputEditText

    private val COUNTER = "COUNTER"
    private val TEXT = "TEXT"

    private val counterPresenter = CounterPresenter(this, CounterModel())
    private val textPresenter = TextPresenter(this, TextModel())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }


    private fun initView() {
        btn_counter.setOnClickListener() { counterPresenter.counterClick() }
        etSomeText  = this.findViewById(R.id.et_some_text)
        btn_text.setOnClickListener() { textPresenter.changeText(etSomeText.text.toString()) }
        btn_math.setOnClickListener() { counterPresenter.calculate() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(COUNTER, counterPresenter.getCounter())
        outState.putString(TEXT, textPresenter.getText())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val counter = savedInstanceState.getInt(COUNTER, 0)

        counterPresenter.changeCounter(counter)
        val someText = savedInstanceState.getString(TEXT, "")
        textPresenter.changeText(someText)
    }

    override fun updateCounter(text: String) {
        btn_counter.text = text
    }

    override fun updateText(text: String) {
        btn_text.text = text
    }

    override fun calculate(text: String) {
        btn_math.text = text
    }
}
