package com.wcp.moviedb.delegates

import android.view.View
import com.wcp.moviedb.data.api.responses.NowShowingResponse

interface NowShowingActionDelegate {
    fun showDetailMovie(view: View, data: NowShowingResponse)
}