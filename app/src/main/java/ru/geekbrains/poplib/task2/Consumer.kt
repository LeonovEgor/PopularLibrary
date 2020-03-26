package ru.geekbrains.poplib.task2

import io.reactivex.rxjava3.disposables.Disposable
import ru.geekbrains.poplib.task2.eventbus.EventBus
import timber.log.Timber

class Consumer(private val name: String) {
    private lateinit var disposable: Disposable

    fun <T> subscribe(bus: EventBus, type: Class<T>) {
        disposable = bus.getEvents(type).subscribe {
            Timber.d("Event on $name - $it")
        }
    }

    fun unSubscribe() {
        disposable.dispose()
    }
}