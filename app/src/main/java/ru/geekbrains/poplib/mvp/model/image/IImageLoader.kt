package ru.geekbrains.poplib.mvp.model.image

import ru.geekbrains.poplib.mvp.model.entity.room.cache.IImageCache

interface IImageLoader<T> {
    val cache: IImageCache
    fun loadInto(url: String, container: T)
}