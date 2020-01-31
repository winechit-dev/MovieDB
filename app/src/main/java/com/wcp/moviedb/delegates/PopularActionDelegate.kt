package com.wcp.moviedb.delegates

import android.view.View
import com.wcp.moviedb.data.api.responses.PopularResponse

interface PopularActionDelegate {
    fun showDetailMovie(view: View, data: PopularResponse)
}