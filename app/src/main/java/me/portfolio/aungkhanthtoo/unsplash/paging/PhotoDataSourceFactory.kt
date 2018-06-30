package me.portfolio.aungkhanthtoo.unsplash.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import me.portfolio.aungkhanthtoo.unsplash.data.vos.Photo
import me.portfolio.aungkhanthtoo.unsplash.network.Api

class PhotoDataSourceFactory(private val apiService: Api): DataSource.Factory<Int, Photo>() {

    val sourceLiveData = MutableLiveData<PhotoDataSource>()

    override fun create(): DataSource<Int, Photo> {
        val dataSource = PhotoDataSource(apiService)
        sourceLiveData.postValue(dataSource)
        return dataSource
    }

}