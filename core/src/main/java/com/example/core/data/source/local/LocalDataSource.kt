package com.example.core.data.source.local

import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    fun getTopRatedMovie(): Flow<List<MovieEntity>> = movieDao.getTopRatedMovie()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(entity: MovieEntity, newState: Boolean){
        entity.isFavorite = newState
        movieDao.updateFavoriteMovie(entity)
    }

}