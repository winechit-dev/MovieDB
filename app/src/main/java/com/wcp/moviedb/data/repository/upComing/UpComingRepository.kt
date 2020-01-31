package com.wcp.moviedb.data.repository.upComing

import com.nyinyi.mymovie.data.api.response.MoviesResponse
import com.nyinyi.nw.themovie.mvi.data.api.responses.upcoming.UpComingResponse
import io.reactivex.Observable

interface UpComingRepository {
    fun loadUpComingMovie(apiKey : String) : Observable<MoviesResponse<UpComingResponse>>
}