package com.example.weater_ua_fun.model.api.responses

import com.google.gson.annotations.SerializedName

data class Forecast(
    val alerts: Alerts?=null,
    val current: Current?=null,
    val forecast: ForecastX?=null,
    val location: Location?=null,
    @SerializedName("air_quality")
    val airQuality: AirQuality?=null
)