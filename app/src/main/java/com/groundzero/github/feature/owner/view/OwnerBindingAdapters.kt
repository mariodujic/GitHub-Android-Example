package com.groundzero.github.feature.owner.view

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setInfoVisibility")
fun TextView.setInfoVisibility(text: String?) {
    visibility = if (text != null && text != "")
        View.VISIBLE
    else
        View.GONE
}