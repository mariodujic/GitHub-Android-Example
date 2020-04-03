package com.groundzero.github.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("setImage")
fun ImageView.setImage(imgUrl: String?) {
    if (imgUrl != null)
        Glide.with(this.context)
            .load(imgUrl)
            .into(this)
}

@BindingAdapter("setAvatar")
fun ImageView.setAvatar(imgUrl: String?) {
    if (imgUrl != null)
        Glide.with(this.context)
            .load(imgUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(this)
}