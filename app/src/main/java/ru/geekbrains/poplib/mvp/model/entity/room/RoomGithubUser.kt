package ru.geekbrains.poplib.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGithubUser(
    @PrimaryKey val login: String,
    val name: String,
    val id: Int,
    val avatarUrl: String,
    val reposUrl: String,
    val publicRepos: Int
)