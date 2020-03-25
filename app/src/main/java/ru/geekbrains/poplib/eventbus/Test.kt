package ru.geekbrains.poplib.eventbus

class Test {
    fun exec() {
        val bus = EventBus()
        val consumer1 = Consumer("consumer1")
        val consumer2 = Consumer("consumer2")

        bus.send(1) // Никто не поймает

        consumer1.subscribe(bus)
        bus.send(2) // Поймает только consumer1

        consumer2.subscribe(bus)
        bus.send(3) // Поймает consumer1 и consumer2

        consumer1.unSubscribe()
        bus.send("4") // Поймает только consumer2

        consumer2.unSubscribe()
        bus.send("5") // Никто не поймает
    }
}