package me.portfolio.aungkhanthtoo.unsplash.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import me.portfolio.aungkhanthtoo.unsplash.data.vos.DataLoadState
import me.portfolio.aungkhanthtoo.unsplash.data.vos.Photo
import me.portfolio.aungkhanthtoo.unsplash.network.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class PhotoDataSource(private val apiService: Api) : PageKeyedDataSource<Int, Photo>() {

    val mDataLoadedLiveData = MutableLiveData<DataLoadState>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Photo>) {
        mDataLoadedLiveData.postValue(DataLoadState.LOADING)
        val call = apiService.getPhotos(1)

        try {
            val response = call.execute()
            mDataLoadedLiveData.postValue(DataLoadState.LOADED)
            callback.onResult(response.body()!!, null, 2)
        } catch (e: IOException) {
            mDataLoadedLiveData.postValue(DataLoadState.FAILURE)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        mDataLoadedLiveData.postValue(DataLoadState.LOADING)

        val call = apiService.getPhotos(params.key)
        call.enqueue(object: Callback<List<Photo>>{
            override fun onFailure(call: Call<List<Photo>>?, t: Throwable?) {
                mDataLoadedLiveData.postValue(DataLoadState.FAILURE)
            }

            override fun onResponse(call: Call<List<Photo>>?, response: Response<List<Photo>>?) {
                if (response?.isSuccessful==true) {
                    val list = response.body()
                    callback.onResult(list!!, params.key + 1)
                    mDataLoadedLiveData.postValue(DataLoadState.LOADED)
                }else{
                    mDataLoadedLiveData.postValue((DataLoadState.FAILURE))
                }

            }
        })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        // ignored
    }
}