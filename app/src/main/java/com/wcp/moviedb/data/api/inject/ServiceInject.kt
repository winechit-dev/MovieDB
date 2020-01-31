package com.wcp.moviedb.data.api.inject

import com.wcp.moviedb.BuildConfig
import com.wcp.moviedb.data.api.services.MovieService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceInject {
    private fun provideRetrofit(baseUrl : String) : Retrofit{
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.readTimeout(30,TimeUnit.SECONDS)
        builder.writeTimeout(30,TimeUnit.SECONDS)

        if(BuildConfig.DEBUG){
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.networkInterceptors().add(httpLoggingInterceptor)
        }
        val okHttpClient = builder.build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }
    fun provideMovieService(baseUrl: String) : MovieService {
        return provideRetrofit(baseUrl).create(MovieService::class.java)
    }
}