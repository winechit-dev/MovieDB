package com.wcp.moviedb.viewModel

import androidx.lifecycle.ViewModel
import com.nyinyi.mymovie.data.api.response.MoviesResponse
import com.nyinyi.nw.themovie.mvi.data.api.responses.upcoming.UpComingResponse
import com.wcp.moviedb.data.repository.upComing.UpComingRepositoryImpl
import com.wcp.moviedb.utilits.NetworkUtils
import com.wcp.moviedb.utilits.Scheduler
import com.wcp.moviedb.utilits.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class UpComingViewModel constructor(
    private val repository: UpComingRepositoryImpl,
    private val compositeDisposable: CompositeDisposable,
    private val isNetwork: Boolean
) : ViewModel() {
    private val networkState : PublishSubject<String> = PublishSubject.create()

    fun loadMovie(success : (MoviesResponse<UpComingResponse>)-> Unit , fail : (String)-> Unit){
        if(!isNetwork){
            networkState.onNext("No Internet Connection.")
            return
        }
        repository.loadUpComingMovie(NetworkUtils.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(it!=null){
                    success(it)
                }
            },{
                fail(it.localizedMessage.toString())
            }).addTo(compositeDisposable)
    }
    fun intrnetAvilableCheck() : PublishSubject<String>{
        return networkState
    }
}