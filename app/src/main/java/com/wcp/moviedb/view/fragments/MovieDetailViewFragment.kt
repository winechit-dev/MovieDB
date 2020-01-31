package com.wcp.moviedb.view.fragments

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import coil.api.load
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nyinyi.mymovie.data.api.response.detail.MovieDetailResponse
import com.wcp.moviedb.R
import com.wcp.moviedb.baseMovie.BaseBottomSheetDialogFragment
import com.wcp.moviedb.utilits.NetworkUtils.IMAGE_URL
import com.wcp.moviedb.utilits.NetworkUtils.MOVIE_ID
import com.wcp.moviedb.utilits.addTo
import com.wcp.moviedb.utilits.inflate
import com.wcp.moviedb.viewModel.MovieDetailViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.content_movie_detail.view.*
import org.koin.android.ext.android.inject

class MovieDetailViewFragment : BaseBottomSheetDialogFragment() {

    private val viewModel : MovieDetailViewModel by inject()
    companion object {
        fun newInstance(movieId : String) : MovieDetailViewFragment {
            val args = Bundle()
            args.putString(MOVIE_ID,movieId)
            val view = MovieDetailViewFragment()
            view.arguments = args
            return view
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.content_movie_detail,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments!!.getString(MOVIE_ID,"")

        viewModel.internetAvailableCheck()
            .subscribe {
                showToast(it)
            }
            .addTo(compositeDisposable)

        viewModel.loadDetail(movieId,{
            bindDetailData(view,it)
        },{
            showToast(it)
        })

        view.ivClose.setOnClickListener {
            dismiss()
        }
    }


    private fun bindDetailData(view: View, it: MovieDetailResponse) {

        view.rating.isEnabled = false
        view.rating.rating = (it.vote_average / 2).toFloat()
        view.movie_title.text = it.title

        view.adult.visibility = if (it.adult) View.VISIBLE else View.GONE

        view.overview.text = it.overview
        view.or_title.text = it.original_title

        view.or_lan.text = if (it.original_language == "en") "English" else it.original_language
        view.tx_release.text = it.release_date

        view.cover_picture.load(IMAGE_URL + it.backdrop_path)
        view.iv_poster.load(IMAGE_URL + it.poster_path)
        view.cover_picture.load(IMAGE_URL + it.backdrop_path)
        /*Glide.with(view.context).load(IMAGE_URL + it.backdrop_path)
            .apply(RequestOptions().placeholder(R.drawable.movie_placeholder).error(R.drawable.movie_placeholder))
            .into(view.cover_picture)

        Glide.with(view.context).load(IMAGE_URL + it.poster_path)
            .apply(RequestOptions().placeholder(R.drawable.movie_placeholder).error(R.drawable.movie_placeholder))
            .into(view.iv_poster)

        Glide.with(view.context).load(IMAGE_URL + it.backdrop_path)
            .apply(RequestOptions().placeholder(R.drawable.movie_placeholder).error(R.drawable.movie_placeholder))
            .into(view.cover_picture)*/


    }


}