package com.wcp.moviedb.di

import com.wcp.moviedb.data.api.inject.ServiceInject
import com.wcp.moviedb.data.repository.detail.MovieDetailRepositoryImpl
import com.wcp.moviedb.data.repository.nowShowing.NowShowingRepositoryImpl
import com.wcp.moviedb.data.repository.popular.PopularRepositoryImpl
import com.wcp.moviedb.data.repository.upComing.UpComingRepositoryImpl
import com.wcp.moviedb.utilits.NetworkUtils
import com.wcp.moviedb.viewModel.MovieDetailViewModel
import com.wcp.moviedb.viewModel.NowShowingViewModel
import com.wcp.moviedb.viewModel.PopularViewModel
import com.wcp.moviedb.viewModel.UpComingViewModel
import io.reactivex.disposables.CompositeDisposable
import org.koin.dsl.module

val applicationModule = module(override = true) {

    single {
        CompositeDisposable()
    }
    single {
        ServiceInject().provideMovieService(getProperty("BASE_URL"))
    }
    single {
        NetworkUtils.isOnline(get())
    }
}
val upComingModule = module {

    single {
        UpComingRepositoryImpl(get())
    }

    single {
        UpComingViewModel(get(), get(), get())
    }
}

val nowShowingModule = module {

    single {
        NowShowingRepositoryImpl(get())
    }

    single {
        NowShowingViewModel(get(), get(), get())
    }
}

val popularModule = module {

    single {
        PopularRepositoryImpl(get())
    }

    single {
        PopularViewModel(get(), get(), get())
    }
}

val detailModule = module {

    single {
        MovieDetailRepositoryImpl(get())
    }

    single {
        MovieDetailViewModel(get(),get(),get())
    }

}
