package com.example.core.data.source.remote

import android.util.Log
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.ResultsItemTopRated
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getTopRatedMovies(): Flow<ApiResponse<List<ResultsItemTopRated>>> {

        return flow {
            try {
                val res = apiService.getTopRatedMovie()
                val data = res.results
                if (data.isNotEmpty()){
                    Log.e("RemoteDataSource 1", res.results.toString())
                    emit(ApiResponse.Success(res.results))

                }else{
                    Log.e("RemoteDataSource 2", res.toString())
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    }

}