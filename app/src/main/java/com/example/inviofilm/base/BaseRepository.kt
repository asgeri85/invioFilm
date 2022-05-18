package com.example.inviofilm.base

import android.util.Log
import com.example.inviofilm.model.ErrorResponseModel
import com.example.inviofilm.utils.NetworkResult
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.Exception

abstract class BaseRepository {

    suspend fun <T> safeApiRequest(apiRequest:suspend ()->T):NetworkResult<T>{
        return withContext(Dispatchers.IO){
            try {
                NetworkResult.Success(apiRequest.invoke())
            }catch (throwable:Throwable){
                when(throwable){
                    is HttpException->{
                        Log.e("yox","girdi")
                        NetworkResult.Error(false, errorParse(throwable.response()?.errorBody()?.toString()))
                    }
                    else->{
                        Log.e("hata","girdi")
                        NetworkResult.Error(true,throwable.localizedMessage)
                    }
                }
            }
        }
    }
}

private fun errorParse(error: String?):String{
    error.let {
        return try {
            val errorResponse= Gson().fromJson(error, ErrorResponseModel::class.java)
            val errorMessage=errorResponse.error
            errorMessage?:"Bilinmeyen hata oluştu"
        }catch (e: Exception){
            "Bilinmeyen hata oluştu"
        }
    }

    return "Bilinmeyen hata oluştu"

}