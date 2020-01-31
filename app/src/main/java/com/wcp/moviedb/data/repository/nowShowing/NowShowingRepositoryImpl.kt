package com.wcp.moviedb.data.repository.nowShowing

import com.nyinyi.mymovie.data.api.response.MoviesResponse
import com.wcp.moviedb.data.api.responses.NowShowingResponse
import com.wcp.moviedb.data.api.services.MovieService
import io.reactivex.Observable

class NowShowingRepositoryImpl constructor(
    private val service: MovieService
) : NowShowingRepository {
    override fun loadNowShowingMovies(apikey: String): Observable<MoviesResponse<NowShowingResponse>> {
        return service.loadNowShowingMovies(apikey)
    }
}