package me.portfolio.aungkhanthtoo.unsplash.adapters

import android.arch.paging.PagedListAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import me.portfolio.aungkhanthtoo.unsplash.R
import me.portfolio.aungkhanthtoo.unsplash.data.vos.Photo
import me.portfolio.aungkhanthtoo.unsplash.utils.diffCallback
import me.portfolio.aungkhanthtoo.unsplash.viewholders.PhotoViewHolder

class PhotoPagedListAdapter: PagedListAdapter<Photo, PhotoViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            PhotoViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.photo_item_view,
                            parent, false))

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bindTo(getItem(position)!!)
    }

}