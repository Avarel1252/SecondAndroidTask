package com.application.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.setImage(photo: String) {
    Glide.with(this)
        .load(photo)
        .into(this)
}