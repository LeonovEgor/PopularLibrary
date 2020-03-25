package ru.geekbrains.poplib.eventbus

import io.reactivex.rxjava3.subjects.PublishSubject

class EventBus {
    private val bus: PublishSubject<Any> = PublishSubject.create()

    fun send(any: Any) = bus.onNext(any)
    fun getObservable() = bus
}