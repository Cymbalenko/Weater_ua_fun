package com.example.weater_ua_fun.model.api.di

import com.example.weater_ua_fun.model.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun baseUrl()="http://api.weatherapi.com/v1/"

    @Provides
    @Singleton
    fun providerRetrofit(baseUrl:String ): ApiService = Retrofit.Builder()
        .baseUrl(baseUrl )
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create() )
        .build()
        .create(ApiService::class.java)
}

