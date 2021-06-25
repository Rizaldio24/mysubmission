package com.example.capstone1.detail

import androidx.lifecycle.ViewModel
import com.example.core.domain.model.Movie
import com.example.core.domain.usecase.MovieUseCase

class DetailViewModel(private val useCase: MovieUseCase): ViewModel(){

    fun setFavorite(movie: Movie, status: Boolean){
        useCase.setFavorite(movie, status)
    }

}