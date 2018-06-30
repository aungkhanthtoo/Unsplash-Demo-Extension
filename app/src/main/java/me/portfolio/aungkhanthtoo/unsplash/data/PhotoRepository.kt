package me.portfolio.aungkhanthtoo.unsplash.data

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import me.portfolio.aungkhanthtoo.unsplash.data.vos.DataLoadState
import me.portfolio.aungkhanthtoo.unsplash.data.vos.Photo

interface PhotoRepository {

    fun getPhotos(): LiveData<PagedList<Photo>>

    fun getDatLoadedState(): LiveData<DataLoadState>
}