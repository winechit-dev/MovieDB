package com.wcp.moviedb.delegates

import android.view.View
import com.nyinyi.nw.themovie.mvi.data.api.responses.upcoming.UpComingResponse

interface UpComingActionDelegate {
    fun showDetailMovie(view: View, data: UpComingResponse)
}