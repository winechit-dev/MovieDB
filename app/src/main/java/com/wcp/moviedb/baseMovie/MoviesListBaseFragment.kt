package com.wcp.moviedb.baseMovie

import android.content.Context
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
import com.wcp.moviedb.R
import com.wcp.moviedb.view.fragments.MovieDetailViewFragment
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_movies_list.*
import org.koin.android.ext.android.inject

abstract class MoviesListBaseFragment<T> : Fragment() {

    val compositeDisposable : CompositeDisposable by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipe_refresh.isRefreshing = true

        swipe_refresh.setOnRefreshListener {
            dataLoad(1)
        }



    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    abstract fun dataLoad(page: Int)

    abstract fun bindMoviesItemData(view: View, data: T)

    fun detailMovieView(movieId : String) {
        val fragment = MovieDetailViewFragment.newInstance(movieId)
        fragment.show(childFragmentManager,"MovieDetail")
    }

    fun toast(message: String) {
        try {
            context?.let {
                val toast = Toast.makeText(it, message?: "No message specified", Toast.LENGTH_SHORT)
                val view = toast.view

                view.background.setColorFilter(
                    ContextCompat.getColor(it, R.color.colorAccent), PorterDuff.Mode.SRC_IN)

                val textView = view.findViewById<TextView>(android.R.id.message)
                textView.setTextColor(ContextCompat.getColor(it, R.color.white))
                textView.gravity = Gravity.CENTER

                toast.show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



}