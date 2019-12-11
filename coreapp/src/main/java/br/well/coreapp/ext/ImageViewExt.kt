package br.well.coreapp.ext

import android.widget.ImageView
import com.bumptech.glide.Glide

const val PRE_FIX_IMAGE = "https://image.tmdb.org/t/p/w500"
fun ImageView.loadImage(imageUrl: String) {
    Glide.with(context)
        .load(PRE_FIX_IMAGE.plus(imageUrl))
        .into(this)
}