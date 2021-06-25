package com.example.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.MovieUseCase

class FavoriteViewModel(useCase: MovieUseCase): ViewModel() {

    val favoriteMovie = useCase.getFavorite().asLiveData()

}