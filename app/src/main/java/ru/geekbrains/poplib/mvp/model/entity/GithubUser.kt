package ru.geekbrains.poplib.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser(
    @Expose val id: Int,
    @Expose val login: String,
    @Expose val name: String,
    @Expose val avatarUrl: String,
    @Expose val reposUrl: String,
    @Expose val publicRepos: Int
) : Parcelable