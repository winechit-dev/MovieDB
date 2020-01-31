package com.wcp.moviedb.data.repository.detail

import com.nyinyi.mymovie.data.api.response.detail.MovieDetailResponse
import io.reactivex.Observable


interface MovieDetailRespository {
    fun loadMovieDetail(movieId : String, apiKey : String) : Observable<MovieDetailResponse>
}