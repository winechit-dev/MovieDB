package com.nyinyi.mymovie.data.api.response

import com.nyinyi.nw.themovie.mvi.data.api.responses.upcoming.Dates

data class MoviesResponse<N>(
    val dates: Dates,
    val page: Int,
    val results: List<N>,
    val total_pages: Int,
    val total_results: Int
)