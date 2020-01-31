package com.wcp.moviedb.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.wcp.moviedb.R
import com.wcp.moviedb.data.api.responses.NowShowingResponse
import com.wcp.moviedb.delegates.NowShowingActionDelegate
import com.wcp.moviedb.utilits.NetworkUtils.IMAGE_URL
import com.wcp.moviedb.utilits.inflate
import kotlinx.android.synthetic.main.item_view_upcomming.view.*

class NowShowingAdapter(private val list: List<NowShowingResponse>,private val nowShowingActionDelegate: NowShowingActionDelegate):RecyclerView.Adapter<NowShowingAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(parent.inflate(R.layout.item_view_upcomming),nowShowingActionDelegate)
    }

    override fun getItemCount():Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val poster = list[position]
        holder.bindPoster(poster)
    }
    class ViewHolder(private val view : View , private val nowShowingActionDelegate: NowShowingActionDelegate) : RecyclerView.ViewHolder(view) , View.OnClickListener  {
        private lateinit var mPoster : NowShowingResponse
        init {
            view.setOnClickListener(this)
        }

        fun bindPoster ( poster : NowShowingResponse){
            mPoster = poster
            val url= IMAGE_URL +mPoster!!.poster_path
          
            // apply coil image library
            view.iv_upcoming_movie.load(url)
        }
        override fun onClick(v: View?) {
            nowShowingActionDelegate.showDetailMovie(v!!,mPoster)
        }
    }
}