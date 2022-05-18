package com.example.inviofilm.ui.home

import com.example.inviofilm.base.BaseRepository
import com.example.inviofilm.network.ApiFactory
import com.example.inviofilm.utils.Constants.API_KEY
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiFactory: ApiFactory):BaseRepository() {
    suspend fun getFilmData(name:String)=safeApiRequest {
        apiFactory.getFilm(name,API_KEY)
    }
}