package com.wcp.moviedb.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.wcp.moviedb.R
import com.wcp.moviedb.adapters.NowShowingAdapter
import com.wcp.moviedb.data.api.responses.NowShowingResponse
import com.wcp.moviedb.delegates.NowShowingActionDelegate
import com.wcp.moviedb.utilits.addTo
import com.wcp.moviedb.utilits.inflate
import com.wcp.moviedb.utilits.toast
import com.wcp.moviedb.viewModel.NowShowingViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_now_showing.*
import org.koin.android.ext.android.inject


/**
 * A simple [Fragment] subclass.
 */
class NowShowingFragment : Fragment() , NowShowingActionDelegate {

    private val viewModel : NowShowingViewModel by inject()

    private val compositeDisposable : CompositeDisposable by inject()

    private lateinit var nowShowingAdapter : NowShowingAdapter
    companion object{
        fun newInstance() : NowShowingFragment = NowShowingFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container!!.inflate(R.layout.fragment_now_showing) //using kotlin extension
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.internetAvailableCheck()
            .subscribe {
                toast(context!!,it)
            }
            .addTo(compositeDisposable)
        viewModel.loadMovie({

            nowShowingAdapter = NowShowingAdapter(it.results,this@NowShowingFragment)
            rv_view.setHasFixedSize(true)
            rv_view.layoutManager = GridLayoutManager(context,2)
            rv_view.adapter = nowShowingAdapter
        }, {
            toast(context!!,it)
        })

    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    override fun showDetailMovie(view: View, data: NowShowingResponse) {
        detailMovieView(data.id.toString())
    }
    fun detailMovieView(movieId : String) {
        val fragment = MovieDetailViewFragment.newInstance(movieId)
        fragment.show(childFragmentManager,"MovieDetail")
    }
    /*fun toast(message: String) {
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
    }*/
}
