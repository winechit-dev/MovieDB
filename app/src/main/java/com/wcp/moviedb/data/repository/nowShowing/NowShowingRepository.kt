package com.wcp.moviedb.data.repository.nowShowing

import com.nyinyi.mymovie.data.api.response.MoviesResponse
import com.wcp.moviedb.data.api.responses.NowShowingResponse
import io.reactivex.Observable


interface NowShowingRepository {
    fun loadNowShowingMovies(apikey : String) : Observable<MoviesResponse<NowShowingResponse>>
}