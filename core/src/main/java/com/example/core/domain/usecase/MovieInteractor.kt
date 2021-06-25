package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import com.example.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val repository: IMovieRepository): MovieUseCase {

    override fun getTopRatedMovie(): Flow<Resource<List<Movie>>> =
        repository.getTopRatedMovies()

    override fun getFavorite(): Flow<List<Movie>> =
        repository.getFavorite()

    override fun setFavorite(movie: Movie, state: Boolean) =
        repository.setFavorite(movie, state)
}