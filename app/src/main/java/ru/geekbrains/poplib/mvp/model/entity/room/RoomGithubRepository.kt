package ru.geekbrains.poplib.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGithubRepository(
    @PrimaryKey val id: String,
    val name: String,
    val forksCount: Int
)