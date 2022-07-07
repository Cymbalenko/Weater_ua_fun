package com.example.weater_ua_fun.ui.setting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weater_ua_fun.base.SettingsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(private val settingsManager: SettingsManager):
    ViewModel(){

        fun setTemperature(position:Int){
            viewModelScope.launch{
                settingsManager.storeTemperature(position)
            }
        }
        suspend fun getTemperature():Int? {
             return settingsManager.readInt(SettingsManager.TEMPERATURE_F_KEY)
        }
        fun setVisibleMiles(position:Int){
            viewModelScope.launch{
                settingsManager.storeVisibleMiles(position)
            }
        }
        suspend fun getVisibleMiles():Int? {
             return settingsManager.readInt(SettingsManager.VISIBLE_MILES_KEY)
        }

        fun setPrecipitationUnit(position:Int){
            viewModelScope.launch{
                settingsManager.storePrecipitationUnit(position)
            }
        }
        suspend fun getPrecipitationUnit():Int? {
             return settingsManager.readInt(SettingsManager.PRECIPITATION_UNIT)
        }
        fun setPressure(position:Int){
            viewModelScope.launch{
                settingsManager.storePressure(position)
            }
        }
        suspend fun getPressure():Int? {
             return settingsManager.readInt(SettingsManager.PRESSURE)
        }

}