package me.portfolio.aungkhanthtoo.unsplash.viewmodels

import android.arch.lifecycle.ViewModel
import me.portfolio.aungkhanthtoo.unsplash.data.PhotoRepositoryImpl

class PhotoListViewModel : ViewModel() {

    val photos = PhotoRepositoryImpl.getPhotos()

    val loadState = PhotoRepositoryImpl.getDatLoadedState()

}