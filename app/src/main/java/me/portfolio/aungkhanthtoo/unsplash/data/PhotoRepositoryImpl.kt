package me.portfolio.aungkhanthtoo.unsplash.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import me.portfolio.aungkhanthtoo.unsplash.data.vos.DataLoadState
import me.portfolio.aungkhanthtoo.unsplash.data.vos.Photo
import me.portfolio.aungkhanthtoo.unsplash.network.Api
import me.portfolio.aungkhanthtoo.unsplash.paging.PhotoDataSourceFactory
import me.portfolio.aungkhanthtoo.unsplash.utils.PAGE_SIZE
import java.util.concurrent.Executors

object PhotoRepositoryImpl : PhotoRepository {

    private val dataSourceFactory = PhotoDataSourceFactory(Api.create())

    private val NETWORK_IO = Executors.newFixedThreadPool(5)

    override fun getPhotos(): LiveData<PagedList<Photo>> {

        val config = PagedList.Config.Builder()
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setPageSize(PAGE_SIZE)
                .setPrefetchDistance(PAGE_SIZE * 2)
                .setEnablePlaceholders(false)
                .build()

        return LivePagedListBuilder(dataSourceFactory, config)
                .setFetchExecutor(NETWORK_IO)
                .setInitialLoadKey(1)
                .build()
    }

    override fun getDatLoadedState(): LiveData<DataLoadState> {
        return Transformations.switchMap(dataSourceFactory.sourceLiveData) {
            it.mDataLoadedLiveData
        }
    }
}