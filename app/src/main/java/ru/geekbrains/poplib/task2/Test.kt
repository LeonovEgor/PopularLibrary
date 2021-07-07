package ru.geekbrains.poplib.task2

import ru.geekbrains.poplib.task2.eventbus.EventBus

class Test {
    fun exec() {
        val bus = EventBus()
        val consumerInt1 = Consumer("consumerInt1")
        val consumerInt2 = Consumer("consumerInt2")
        val consumerString = Consumer("consumerString")

        bus.send(1) // Никто не поймает

        consumerInt1.subscribe(bus, Integer::class.java) // Подписка только на Int
        consumerString.subscribe(bus, String::class.java) // Подписка только на String
        bus.send(2) // Поймает только consumerInt1

        bus.send("testText") // Поймает только consumerString

        consumerInt2.subscribe(bus, Integer::class.java)
        bus.send(3) // Поймает consumerInt1 и consumerInt2

        consumerInt1.unSubscribe()
        bus.send(4) // Поймает только consumerInt2

        consumerInt2.unSubscribe()
        bus.send(5) // Никто не поймает

        consumerString.unSubscribe()
    }
}