package com.wcp.moviedb.data.api.services

import com.nyinyi.mymovie.data.api.response.MoviesResponse
import com.nyinyi.mymovie.data.api.response.detail.MovieDetailResponse
import com.nyinyi.nw.themovie.mvi.data.api.responses.upcoming.UpComingResponse
import com.wcp.moviedb.data.api.responses.NowShowingResponse
import com.wcp.moviedb.data.api.responses.PopularResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("upcoming")
    fun loadUpComingMovies(
        @Query("api_key") apiKey: String
    ): Observable<MoviesResponse<UpComingResponse>>

    @GET("now_playing")
    fun loadNowShowingMovies(
        @Query("api_key") apiKey: String
    ): Observable<MoviesResponse<NowShowingResponse>>

    @GET("popular")
    fun loaPopularMovies(
        @Query("api_key") apiKey: String
    ): Observable<MoviesResponse<PopularResponse>>


    @GET("{movie_id}")
    fun loadMovieDetails(
        @Path("movie_id") movieId : String,
        @Query("api_key") apiKey : String
    ): Observable<MovieDetailResponse>

}