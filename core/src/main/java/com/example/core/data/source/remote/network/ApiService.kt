package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.ResponseTopRated
import com.example.core.utils.DataMapper
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(@Query("api_key") key: String = DataMapper.API_MOVIE): ResponseTopRated

}