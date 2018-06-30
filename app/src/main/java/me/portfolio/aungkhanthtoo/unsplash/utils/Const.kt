package me.portfolio.aungkhanthtoo.unsplash.utils

import android.support.v7.util.DiffUtil
import me.portfolio.aungkhanthtoo.unsplash.data.vos.Photo

const val API_AUTHORIZATION = "Authorization: Client-ID "

const val ACCESS_KEY = "16f86d4d1699643f793c312dfa52b2a173893d324b6d9f26d2a8d96651195c2b"

const val PAGE_SIZE = 10

val diffCallback = object : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean =
            oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean =
            oldItem == newItem
}
