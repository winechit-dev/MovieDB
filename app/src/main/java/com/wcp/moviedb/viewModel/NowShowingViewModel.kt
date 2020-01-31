package com.wcp.moviedb.viewModel

import androidx.lifecycle.ViewModel
import com.nyinyi.mymovie.data.api.response.MoviesResponse
import com.wcp.moviedb.data.api.responses.NowShowingResponse
import com.wcp.moviedb.data.repository.nowShowing.NowShowingRepositoryImpl
import com.wcp.moviedb.utilits.NetworkUtils.API_KEY
import com.wcp.moviedb.utilits.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class NowShowingViewModel constructor(
    private val repository: NowShowingRepositoryImpl,
    private val compositeDisposable: CompositeDisposable,
    private val isNetwork : Boolean
) : ViewModel() {

    private val networkState : PublishSubject<String> = PublishSubject.create()

    fun loadMovie(success: (MoviesResponse<NowShowingResponse>) -> Unit, fail: (String) -> Unit) {

        if (!isNetwork) {
            networkState.onNext("No internet connection.")
            return
        }
        repository.loadNowShowingMovies(API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it != null) {
                    success(it)
                }
            }, {
                fail(it.localizedMessage)
            })
            .addTo(compositeDisposable)

    }

    fun internetAvailableCheck() : PublishSubject<String> {
        return networkState
    }
}