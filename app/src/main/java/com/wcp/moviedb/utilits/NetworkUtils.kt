package com.wcp.moviedb.utilits

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo



object NetworkUtils {
    val SEARCH_BASE_URL = "https://api.themoviedb.org/3/"
    val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"

    val PARAM_API_KEY = "api_key"
    val API_KEY = "833a499868b5e5b688ef57dcb3e32ff1"
    val PARAM_QUERY = "query"

    val API_GET_MOVIE_DETAIL = "{movie_id}"
    val PARAM_MOVIE_ID = "movie_id"

    val MOVIE_ID = "MOVIE_ID"

    fun isOnline(context: Context): Boolean{
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var netInfo: NetworkInfo? = null
        if (cm != null) {
            netInfo = cm.activeNetworkInfo
        }
        return netInfo!= null && netInfo.isConnected
    }
}