package com.example.weater_ua_fun.model.api

import com.example.weater_ua_fun.model.api.responses.Current
import com.example.weater_ua_fun.model.api.responses.Forecast
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers(  "Content-Type: application/json;charset=UTF-8" )
    @GET("forecast.json")
    suspend fun getForecast(
        @Query("q") id:String,
        @Query("lang") lang:String,
        @Query("aqi") aqi:String="yes",
        @Query("key") key:String="8e77104a44424b04a0794903222806"
    ):Response<Forecast>

    @Headers(  "Content-Type: application/json;charset=UTF-8" )
    @GET("current.json")
    suspend fun getCurrent(
        @Query("q") id:String,
        @Query("lang") lang:String,
        @Query("aqi") aqi:String="yes",
        @Query("key") key:String="8e77104a44424b04a0794903222806"
    ):Response<Forecast>
}