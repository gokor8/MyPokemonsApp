package com.example.lib.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLi = HttpLoggingInterceptor()
        httpLi.level = HttpLoggingInterceptor.Level.HEADERS

        return httpLi
    }

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideApiService(okHttp: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl("https://api.pokemontcg.io/")
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ApiService::class.java)

}