package com.groundzero.github.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.groundzero.github.R
import com.groundzero.github.utils.parseServerTime

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

@BindingAdapter("setFormattedTime")
fun TextView.setFormattedTime(time: String?) {
    if (time != null)
        text = context.getString(R.string.item_search_last_update, parseServerTime(time))
}

@BindingAdapter("setInfoVisibility")
fun TextView.setInfoVisibility(text: String?) {
    visibility = if (text != null && text != "")
        View.VISIBLE
    else
        View.GONE
}