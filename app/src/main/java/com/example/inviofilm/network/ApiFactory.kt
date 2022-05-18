package com.example.inviofilm.network

import com.example.inviofilm.model.FilmResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiFactory {

    @GET("/")
    suspend fun getFilm(@Query("t") name:String,@Query("apikey") apikey:String):FilmResponseModel

}