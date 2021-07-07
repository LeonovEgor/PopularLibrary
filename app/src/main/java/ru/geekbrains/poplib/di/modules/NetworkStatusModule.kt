package ru.geekbrains.poplib.di.modules

import dagger.Module
import dagger.Provides
import ru.geekbrains.poplib.ui.App
import ru.geekbrains.poplib.ui.network.NetworkStatus
import javax.inject.Singleton

@Module
class NetworkStatusModule {

    @Singleton
    @Provides
    fun networkStatus(app: App): NetworkStatus {
        return NetworkStatus(app)
    }
}