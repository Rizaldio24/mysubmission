package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getTopRatedMovie(): Flow<Resource<List<Movie>>>

    fun getFavorite(): Flow<List<Movie>>

    fun setFavorite(movie: Movie, state: Boolean)

}