package me.portfolio.aungkhanthtoo.unsplash.data.vos

import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("profile_image")
        val profileImage: ProfileImage
)

data class ProfileImage(
        @SerializedName("medium")
        val mediumImage: String
)