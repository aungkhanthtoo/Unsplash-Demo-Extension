package me.portfolio.aungkhanthtoo.unsplash.network

import me.portfolio.aungkhanthtoo.unsplash.data.vos.Photo
import me.portfolio.aungkhanthtoo.unsplash.utils.ACCESS_KEY
import me.portfolio.aungkhanthtoo.unsplash.utils.API_AUTHORIZATION
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


interface Api {

    @GET("photos")
    @Headers(API_AUTHORIZATION + ACCESS_KEY)
    fun getPhotos(@Query("page") pageNum: Int, @Query("order_by") order: String = "popular"): Call<List<Photo>>

    companion object {
        private const val BASE_URL = "https://api.unsplash.com/"

        fun create(): Api = create(HttpUrl.parse(BASE_URL)!!)

        fun create(httpUrl: HttpUrl): Api {
            val client = OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build()

            return Retrofit.Builder()
                    .baseUrl(httpUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(Api::class.java)
        }
    }

}