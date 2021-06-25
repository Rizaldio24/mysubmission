package com.example.capstone1.di

import com.example.core.domain.usecase.MovieInteractor
import com.example.core.domain.usecase.MovieUseCase
import com.example.capstone1.detail.DetailViewModel
import com.example.capstone1.home.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {

    viewModel{MainViewModel(get())}
    viewModel{ DetailViewModel(get()) }

}