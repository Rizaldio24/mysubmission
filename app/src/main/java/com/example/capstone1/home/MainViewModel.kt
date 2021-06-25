package com.example.capstone1.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.MovieUseCase

class MainViewModel(movieUseCase: MovieUseCase): ViewModel() {

    val getTopRated = movieUseCase.getTopRatedMovie().asLiveData()

}