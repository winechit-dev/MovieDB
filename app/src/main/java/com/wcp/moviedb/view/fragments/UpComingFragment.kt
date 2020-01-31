package com.wcp.moviedb.view.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.nyinyi.nw.themovie.mvi.data.api.responses.upcoming.UpComingResponse

import com.wcp.moviedb.R
import com.wcp.moviedb.adapters.UpComingAdapter
import com.wcp.moviedb.delegates.UpComingActionDelegate
import com.wcp.moviedb.utilits.addTo
import com.wcp.moviedb.utilits.inflate
import com.wcp.moviedb.utilits.toast
import com.wcp.moviedb.viewModel.UpComingViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_up_coming.*
import org.koin.android.ext.android.inject

class UpComingFragment : Fragment() ,UpComingActionDelegate{
    private val viewModel :UpComingViewModel by inject()
    private val compositeDisposable : CompositeDisposable by inject()
    private lateinit var upComingAdapter: UpComingAdapter
    companion object {
        fun newInstance(): UpComingFragment = UpComingFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container!!.inflate(R.layout.fragment_up_coming) //using kotlin extension
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.intrnetAvilableCheck()
            .subscribe{
                toast(context!! , it)
            }.addTo(compositeDisposable)

        viewModel.loadMovie({
            upComingAdapter = UpComingAdapter(it.results ,this@UpComingFragment)
            rv_view.setHasFixedSize(true)
            rv_view.layoutManager = GridLayoutManager(context ,2)
            rv_view.adapter = upComingAdapter
        },{
            toast(context!!, it)
        })
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    override fun showDetailMovie(view: View, data: UpComingResponse) {
        val fragment=MovieDetailViewFragment.newInstance(data.id.toString())
        fragment.show(childFragmentManager,"MovieDetail")
    }


}
