package me.portfolio.aungkhanthtoo.unsplash.data.vos

import com.google.gson.annotations.SerializedName

data class Photo(
        @SerializedName("id")
        val id: String,
        @SerializedName("urls")
        val url: Url,
        @SerializedName("user")
        val user: User,
        @SerializedName("width")
        val width: Int,
        @SerializedName("height")
        val height: Int
) {
    val heightRatio: Double
        get() = height.toDouble() / width.toDouble()

}

data class Url(
        @SerializedName("regular")
        val regularImage: String
)

