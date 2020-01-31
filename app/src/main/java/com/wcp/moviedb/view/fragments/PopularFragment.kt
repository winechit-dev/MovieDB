package com.wcp.moviedb.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.wcp.moviedb.R
import com.wcp.moviedb.adapters.NowShowingAdapter
import com.wcp.moviedb.adapters.PopularAdapter
import com.wcp.moviedb.data.api.responses.PopularResponse
import com.wcp.moviedb.delegates.PopularActionDelegate
import com.wcp.moviedb.utilits.addTo
import com.wcp.moviedb.utilits.inflate
import com.wcp.moviedb.utilits.toast
import com.wcp.moviedb.viewModel.PopularViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_popular.*
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class PopularFragment : Fragment() ,PopularActionDelegate {

    private val viewModel: PopularViewModel by inject()

    private val compositeDisposable : CompositeDisposable by inject()

    private lateinit var popularAdapter: PopularAdapter

    companion object {
        fun newInstance(): PopularFragment =PopularFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return container!!.inflate(R.layout.fragment_popular)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.internetAvailableCheck()
            .subscribe {
                toast(context!!,it)
            }
            .addTo(compositeDisposable)
        viewModel.loadMovies({
            popularAdapter = PopularAdapter(it.results , this@PopularFragment)
            rv_view.setHasFixedSize(true)
            rv_view.layoutManager = GridLayoutManager(context,2)
            rv_view.adapter = popularAdapter
        },{
            toast(context!!, it)
        })
    }

    override fun showDetailMovie(view: View, data: PopularResponse) {
        val fragment = MovieDetailViewFragment.newInstance(movieId = data.id.toString())
        fragment.show(childFragmentManager , "MovieDetail")
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

}
