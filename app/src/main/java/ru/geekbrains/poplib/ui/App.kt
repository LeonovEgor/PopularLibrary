package ru.geekbrains.poplib.ui

import android.app.Application
import ru.geekbrains.poplib.task2.Test
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import timber.log.Timber

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        Timber.plant(Timber.DebugTree())
        //BusEvent test
        val test = Test()
        test.exec()
    }

    val navigatorHolder get() = cicerone.navigatorHolder
    val router get() = cicerone.router
}