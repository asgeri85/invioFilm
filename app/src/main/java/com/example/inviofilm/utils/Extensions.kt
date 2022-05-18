package com.example.inviofilm.utils

import android.widget.ImageView
import coil.load
import com.example.inviofilm.R

fun ImageView.loadUrl(url:String?){
    url?.let {
        this.load(it){
            crossfade(true)
            crossfade(500)
            placeholder(R.drawable.ic_baseline_downloading_24)
        }
    }

}