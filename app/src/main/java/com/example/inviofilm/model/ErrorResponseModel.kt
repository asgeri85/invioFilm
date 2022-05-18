package com.example.inviofilm.model


import com.google.gson.annotations.SerializedName

data class ErrorResponseModel(
    @SerializedName("Error")
    val error: String?,
    @SerializedName("Response")
    val response: String?
)