package com.wcp.moviedb.data.repository.popular

import com.nyinyi.mymovie.data.api.response.MoviesResponse
import com.wcp.moviedb.data.api.responses.PopularResponse
import io.reactivex.Observable

interface PopularRepository {
    fun loadPopularMovies(apiKey : String) : Observable<MoviesResponse<PopularResponse>>
}