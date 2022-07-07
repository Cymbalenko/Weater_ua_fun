package com.example.weater_ua_fun.ui.currentday

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weater_ua_fun.base.SettingsManager
import com.example.weater_ua_fun.model.api.responses.Current
import com.example.weater_ua_fun.model.api.responses.Forecast
import com.example.weater_ua_fun.model.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentDayViewModel @Inject constructor(private val repository: ApiRepository,private val settingsManager: SettingsManager):ViewModel() {



    private var  _forecast = MutableStateFlow<Forecast>(Forecast())
    val forecast:StateFlow<Forecast> = _forecast.asStateFlow()

    fun getCurrent(){
        viewModelScope.launch {
            repository.getForecast("Киев","ru").let {
                if(it.isSuccessful){
                    Log.d("getCurrent","Succes: \n${it.body()}")
                    it.body()?.let {
                        _forecast.value=it
                    }
                    Log.d("getCurrent","forecast:\n ${forecast}")
                }else{
                    Log.d("getCurrent","Error: ${it.errorBody()}")
                }
            }

        }
    }
    suspend fun getTemperature():Int? {
        return settingsManager.readInt(SettingsManager.TEMPERATURE_F_KEY)
    }
    suspend fun getVisibleMiles():Int? {
        return settingsManager.readInt(SettingsManager.VISIBLE_MILES_KEY)
    }
    suspend fun getPressure():Int? {
        return settingsManager.readInt(SettingsManager.PRESSURE)
    }
    suspend fun getPrecipitation():Int? {
        return settingsManager.readInt(SettingsManager.PRECIPITATION_UNIT)
    }

}