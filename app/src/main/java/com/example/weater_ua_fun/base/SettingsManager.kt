package com.example.weater_ua_fun.base

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import com.example.weater_ua_fun.R
import com.example.weater_ua_fun.WeaterApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

    @Singleton
    class SettingsManager @Inject constructor(@ApplicationContext appContext: Context) {
        private val dataStore = appContext.createDataStore("settings")

        companion object{
            val TEMPERATURE_F_KEY:Preferences.Key<Int> = preferencesKey<Int>("TEMPERATURE_F")
            val VISIBLE_MILES_KEY:Preferences.Key<Int> = preferencesKey<Int>("VISIBLE_MILES")
            val PRECIPITATION_UNIT:Preferences.Key<Int> = preferencesKey<Int>("PRECIPITATION_UNIT")
            val PRESSURE:Preferences.Key<Int> = preferencesKey<Int>("PRESSURE")

        }
        suspend fun save(key: Preferences.Key<Boolean>, value: Boolean) {
            dataStore.edit {
                it[key] = value
            }

        }
        suspend fun save(key: Preferences.Key<String>, value: String) {
            dataStore.edit {
                it[key] = value
            }
        }
        suspend fun save(key: Preferences.Key<Int>, value: Int) {
            dataStore.edit {
                it[key] = value
            }
        }
        suspend fun read(key: String): String? {
            val dataStoreKey = preferencesKey<String>(key)
            val preferences = dataStore.data.first()
            return preferences[dataStoreKey]
        }
        suspend fun read(key:  Preferences.Key<String>): String? {
            val preferences = dataStore.data.first()
            return preferences[key]
        }
        suspend fun readInt(key:  Preferences.Key<Int>): Int? {
            val preferences = dataStore.data.first()
            return preferences[key]
        }
        suspend fun readBoolean(key:  Preferences.Key<Boolean>): Boolean? {
            val preferences = dataStore.data.first()
            return preferences[key]
        }
        suspend fun storeTemperature(temperature:Int){
            save(TEMPERATURE_F_KEY,temperature)
        }
        suspend fun storeVisibleMiles(visible:Int){
            save(VISIBLE_MILES_KEY,visible)
        }
        suspend fun storePrecipitationUnit(visible:Int){
            save(PRECIPITATION_UNIT,visible)
        }
        suspend fun storePressure(visible:Int){
            save(PRESSURE,visible)
        }
    }