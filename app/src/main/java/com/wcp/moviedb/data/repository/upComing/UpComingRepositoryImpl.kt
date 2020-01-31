package com.wcp.moviedb.data.repository.upComing

import com.nyinyi.mymovie.data.api.response.MoviesResponse
import com.nyinyi.nw.themovie.mvi.data.api.responses.upcoming.UpComingResponse
import com.wcp.moviedb.data.api.services.MovieService
import io.reactivex.Observable

class UpComingRepositoryImpl constructor(
    private val service: MovieService
) : UpComingRepository{
    override fun loadUpComingMovie(apiKey: String): Observable<MoviesResponse<UpComingResponse>> {
        return service.loadUpComingMovies(apiKey)
    }
}