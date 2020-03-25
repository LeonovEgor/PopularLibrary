package ru.geekbrains.poplib.eventbus

import io.reactivex.rxjava3.disposables.Disposable
import timber.log.Timber

class Consumer(private val name: String) {
    private lateinit var disposable: Disposable

    fun subscribe(bus: EventBus) {
        disposable = bus.getObservable().subscribe() {
            Timber.d("Event on $name - $it")
        }
    }

    fun unSubscribe() {
        disposable.dispose()
    }
}