package ru.geekbrains.poplib.di.modules

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class CiceroneModule {

    @Singleton
    @Provides
    fun cicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    fun navigatorHolder(cicerone: Cicerone<Router>) =
        cicerone.navigatorHolder

    @Provides
    fun router(cicerone: Cicerone<Router>) = cicerone.router

}