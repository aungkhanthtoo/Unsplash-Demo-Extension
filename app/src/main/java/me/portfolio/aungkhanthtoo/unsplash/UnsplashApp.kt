package me.portfolio.aungkhanthtoo.unsplash

import android.app.Application
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class UnsplashApp : Application() {

    override fun onCreate() {
        super.onCreate()
        val picasso = Picasso.Builder(this)
                .downloader(OkHttp3Downloader(cacheDir, 250000000))
                .build()

        Picasso.setSingletonInstance(picasso)
    }
}