package com.example.inviofilm.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.inviofilm.utils.loadUrl

object BindingAdapter {

    @BindingAdapter("load_image")
    @JvmStatic
    fun loadUrlImage(imageView: ImageView,name:String?){
        name?.let {
            imageView.loadUrl(name)
        }

    }
}