package com.example.weater_ua_fun.model.repository

import com.example.weater_ua_fun.model.api.ApiService
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getForecast(city:String,lang:String)=apiService.getForecast(city,lang)
    suspend fun getCurrent(city:String,lang:String)=apiService.getCurrent(city,lang)


}