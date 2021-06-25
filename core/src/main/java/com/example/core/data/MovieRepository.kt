package com.example.core.data

import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.ResultsItemTopRated
import com.example.core.domain.model.Movie
import com.example.core.domain.repository.IMovieRepository
import com.example.core.utils.AppExecutors
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
): IMovieRepository {

    override fun getTopRatedMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<ResultsItemTopRated>>(){
            override fun onFetchFailed() {
                super.onFetchFailed()
            }

            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getTopRatedMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItemTopRated>>> =
                remoteDataSource.getTopRatedMovies()

            override suspend fun saveCallResult(data: List<ResultsItemTopRated>) {
                val movieList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getFavorite(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavorite(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntities(movie)
        appExecutors.diskIO().execute{
            localDataSource.setFavoriteMovie(movieEntity, state)
        }
    }
}