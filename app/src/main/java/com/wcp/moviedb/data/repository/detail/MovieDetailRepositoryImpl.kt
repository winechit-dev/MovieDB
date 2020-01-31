package com.wcp.moviedb.data.repository.detail

import com.nyinyi.mymovie.data.api.response.detail.MovieDetailResponse
import com.wcp.moviedb.data.api.services.MovieService
import io.reactivex.Observable

class MovieDetailRepositoryImpl constructor(
    private val service: MovieService
) : MovieDetailRespository {
    override fun loadMovieDetail(movieId: String, apiKey: String): Observable<MovieDetailResponse> {
        return service.loadMovieDetails(movieId, apiKey)
    }
}