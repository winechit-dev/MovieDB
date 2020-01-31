package com.wcp.moviedb.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.wcp.moviedb.R
import com.wcp.moviedb.data.api.responses.NowShowingResponse
import com.wcp.moviedb.data.api.responses.PopularResponse
import com.wcp.moviedb.delegates.PopularActionDelegate
import com.wcp.moviedb.utilits.NetworkUtils.IMAGE_URL
import com.wcp.moviedb.utilits.inflate
import kotlinx.android.synthetic.main.item_view_upcomming.view.*

class PopularAdapter(private val list: List<PopularResponse>, private val popularActionDelegate: PopularActionDelegate):RecyclerView.Adapter<PopularAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(parent.inflate(R.layout.item_view_upcomming),popularActionDelegate)
    }

    override fun getItemCount():Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val poster = list[position]
        holder.bindPoster(poster)
    }
    class ViewHolder(private val view : View , private val popularActionDelegate: PopularActionDelegate) : RecyclerView.ViewHolder(view) , View.OnClickListener  {
        private lateinit var mPoster : PopularResponse
        init {
            view.setOnClickListener(this)
        }

        fun bindPoster ( poster : PopularResponse){
            mPoster = poster
            val url= IMAGE_URL +mPoster!!.poster_path
          
            // apply coil image library
            view.iv_upcoming_movie.load(url)
        }
        override fun onClick(v: View?) {
            popularActionDelegate.showDetailMovie(v!!,mPoster)
        }
    }
}