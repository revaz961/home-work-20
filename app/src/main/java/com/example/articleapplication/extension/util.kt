package com.example.articleapplication.extension

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.articleapplication.R

fun ImageView.load(
    url: String,
    placeholder: Int = R.drawable.image_not_found,
    error: Int = R.drawable.image_not_found
) {
    Glide.with(this.context)
        .load(url)
        .placeholder(placeholder)
        .error(error)
        .into(this)
}

fun View.isVisible(visible:Boolean){
    if(visible)
        this.visibility = View.VISIBLE
    else
        this.visibility = View.GONE
}