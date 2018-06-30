package me.portfolio.aungkhanthtoo.unsplash.utils

import android.app.Activity
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import me.portfolio.aungkhanthtoo.unsplash.R
import me.portfolio.aungkhanthtoo.unsplash.activities.MainActivity
import me.portfolio.aungkhanthtoo.unsplash.data.vos.Photo
import me.portfolio.aungkhanthtoo.unsplash.data.vos.ProfileImage
import me.portfolio.aungkhanthtoo.unsplash.data.vos.Url
import me.portfolio.aungkhanthtoo.unsplash.data.vos.User
import me.portfolio.aungkhanthtoo.unsplash.viewholders.PhotoViewHolder
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object SampleUtils {

    private lateinit var mSampleList: List<Photo>

    fun generateSamples(activity: Activity) {
        mSampleList = getSampleData(activity)
    }

    fun setUpWithRecycler(recycler: RecyclerView, dataFromNetwork: List<Photo> ) {

        recycler.adapter = object : ListAdapter<Photo, PhotoViewHolder>(diffCallback){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
                return PhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.photo_item_view, parent, false))
            }

            override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
               holder.bindTo(getItem(position))
            }

        }.apply { submitList(dataFromNetwork) }
    }

    private fun getSampleData(activity: Activity): List<Photo> {
        val list = ArrayList<Photo>()
        try {
            val photos = JSONArray(readSample(activity))
            for (i in 0 until photos.length()) {
                val data = photos.getJSONObject(i)
                val userJson = data.getJSONObject("user")
                val userProfile = userJson.getJSONObject("profile_image")
                val profileUrl = userProfile.getString("medium")
                val url = data.getJSONObject("url")
                val imageUrl = url.getString("regular")

                val user = User(
                        userJson.getString("id"),
                        userJson.getString("name"),
                        ProfileImage(profileUrl))
                val photo = Photo(
                        data.getString("id"),
                        Url(imageUrl),
                        user, 100, 100)
                Log.d(MainActivity.TAG, "generatePhotos: " + photo.toString())
                list.add(photo)
            }
            return list
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return ArrayList()
    }

    private fun readSample(activity: Activity): String? {
        val jsonString = StringBuilder()
        try {
            val inputStream = activity.assets.open("sample.json")
            val buffer = BufferedReader(InputStreamReader(inputStream/*, Charset.forName("UTF-8")*/))

            var response = buffer.readLine()
            while (response != null) {
                jsonString.append(response)
                response = buffer.readLine()
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return jsonString.toString()
    }

}

