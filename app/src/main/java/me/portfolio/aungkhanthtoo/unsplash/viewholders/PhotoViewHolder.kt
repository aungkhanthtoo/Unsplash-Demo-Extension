package me.portfolio.aungkhanthtoo.unsplash.viewholders


import android.support.v7.widget.RecyclerView

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.photo_item_view.view.*
import me.portfolio.aungkhanthtoo.unsplash.components.DynamicImageView
import me.portfolio.aungkhanthtoo.unsplash.data.vos.Photo
import java.lang.Exception


class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val photoView: DynamicImageView = itemView.iv_photo_item
    private val userPic: ImageView = itemView.iv_avatar_item
    private val nameText: TextView = itemView.tv_photographer_item
    private val progress = itemView.loading_indicator


    fun bindTo(photo: Photo) {
        if(progress.visibility == View.INVISIBLE) progress.visibility = View.VISIBLE
        nameText.text = photo.user.name
        Picasso.get()
                .load(photo.user.profileImage.mediumImage)
                .noFade()
                .into(userPic)

        photoView.heightRatio = photo.heightRatio
        loadPhoto(true, photo.url.regularImage)
    }

    private fun loadPhoto(offline: Boolean, url: String) {
        with(Picasso.get()
                .load(url)
                .fit()) {
            if (offline) networkPolicy(NetworkPolicy.OFFLINE)
            into(photoView, object : Callback {
                override fun onSuccess() {
                    if (progress.visibility == View.VISIBLE) {
                        progress.visibility = View.INVISIBLE
                    }
                }

                override fun onError(e: Exception?) {
                    loadPhoto(false, url)
                }
            })

        }

    }

}