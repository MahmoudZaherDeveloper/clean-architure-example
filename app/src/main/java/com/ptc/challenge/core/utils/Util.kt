package com.ptc.challenge.core.utils

import android.content.Context
import android.graphics.Paint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.ptc.challenge.R
import com.ptc.challenge.data.model.pojo.Currency


fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 5f
        centerRadius = 30f
        start()
    }
}

fun ImageView.loadImage(uri: String, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .transition(DrawableTransitionOptions.withCrossFade()) //fading animation
        .into(this)
}

@BindingAdapter("android:imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
    if (url != null) {
        imageView.loadImage(url, getProgressDrawable(imageView.context))
    }
}

@BindingAdapter("android:getCurrency")
fun getCurrencySymbol(textView: TextView, currency: Currency): String {
    return currency.currencySymbol
}

@BindingAdapter("is_strike")
fun TextView.strikeThrough(strikeThrough: Boolean = true) {
    this.paintFlags = this.paintFlags or
            if (strikeThrough) {
                Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
}