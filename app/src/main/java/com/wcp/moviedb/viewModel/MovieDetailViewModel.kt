package com.wcp.moviedb.viewModel

import androidx.lifecycle.ViewModel
import com.nyinyi.mymovie.data.api.response.detail.MovieDetailResponse
import com.wcp.moviedb.data.repository.detail.MovieDetailRepositoryImpl
import com.wcp.moviedb.utilits.NetworkUtils.API_KEY
import com.wcp.moviedb.utilits.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class MovieDetailViewModel constructor(
    private val repository : MovieDetailRepositoryImpl,
    private val compositeDisposable: CompositeDisposable,
    private val isNetwork: Boolean
) : ViewModel() {

    private val networkState : PublishSubject<String> = PublishSubject.create()

    fun loadDetail(movieId : String, success : (MovieDetailResponse) -> Unit, fail: (String) -> Unit) {
        if (!isNetwork) {
            networkState.onNext("No internet connection.")
            return
        }

        repository.loadMovieDetail(movieId, API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                success(it)
            },{
                fail(it.localizedMessage)
            })
            .addTo(compositeDisposable)

    }

    fun internetAvailableCheck() : PublishSubject<String> {
        return networkState
    }

}