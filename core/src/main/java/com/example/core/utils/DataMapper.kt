package com.example.core.utils

import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.remote.response.ResultsItemTopRated
import com.example.core.domain.model.Movie

object DataMapper {

    val API_MOVIE = "34c0b4cf622691cbe06f5bcc7a2e0bca"
    val imageLink = "https://image.tmdb.org/t/p/" + "w185"

    fun mapResponseToEntities(input: List<ResultsItemTopRated>): List<MovieEntity>{
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                overview = it.overview,
                title = it.title,
                backdropPath = it.backdropPath,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                overview = it.overview,
                title = it.title,
                backdropPath = it.backdropPath,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntities(input: Movie) =
        MovieEntity(
            id = input.id,
            overview = input.overview,
            title = input.title,
            backdropPath = input.backdropPath,
            isFavorite = input.isFavorite
        )
}