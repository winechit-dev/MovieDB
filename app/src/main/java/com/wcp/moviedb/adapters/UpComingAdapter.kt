package com.wcp.moviedb.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.nyinyi.nw.themovie.mvi.data.api.responses.upcoming.UpComingResponse
import com.wcp.moviedb.R
import com.wcp.moviedb.data.api.responses.NowShowingResponse
import com.wcp.moviedb.delegates.NowShowingActionDelegate
import com.wcp.moviedb.delegates.UpComingActionDelegate
import com.wcp.moviedb.utilits.NetworkUtils
import com.wcp.moviedb.utilits.inflate
import kotlinx.android.synthetic.main.item_view_upcomming.view.*

class UpComingAdapter(private val list: List<UpComingResponse>,private val upComingActionDelegate : UpComingActionDelegate):RecyclerView.Adapter<UpComingAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_view_upcomming),upComingActionDelegate)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val poster = list[position]
        holder.bindPoster(poster)
    }
    class ViewHolder(private val view : View, private val upComingActionDelegate: UpComingActionDelegate) : RecyclerView.ViewHolder(view) , View.OnClickListener  {
        private lateinit var mPoster : UpComingResponse
        init {
            view.setOnClickListener(this)
        }

        fun bindPoster ( poster : UpComingResponse){
            mPoster = poster
            val url= NetworkUtils.IMAGE_URL +mPoster!!.poster_path

            // apply coil image library
            view.iv_upcoming_movie.load(url)
        }
        override fun onClick(v: View?) {
            upComingActionDelegate.showDetailMovie(v!!,mPoster)
        }
    }
}