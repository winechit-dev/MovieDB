package com.wcp.moviedb.data.repository.popular

import com.nyinyi.mymovie.data.api.response.MoviesResponse
import com.wcp.moviedb.data.api.responses.PopularResponse
import com.wcp.moviedb.data.api.services.MovieService
import io.reactivex.Observable

class PopularRepositoryImpl constructor(
    private val service: MovieService
) : PopularRepository {
    override fun loadPopularMovies(apiKey: String): Observable<MoviesResponse<PopularResponse>> {
        return service.loaPopularMovies(apiKey)
    }
}