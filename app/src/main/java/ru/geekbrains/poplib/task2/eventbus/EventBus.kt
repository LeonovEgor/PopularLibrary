package ru.geekbrains.poplib.task2.eventbus

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class EventBus {

    private val bus: PublishSubject<Any> = PublishSubject.create()

    fun send(event: Any) = bus.onNext(event)

    fun <T> getEvents(type: Class<T>): @NonNull Observable<Any> = bus.filter {
        type.isAssignableFrom(it.javaClass)
    }
}