package com.example.weater_ua_fun.ui.currentday

import android.graphics.Color
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.weater_ua_fun.R
import com.example.weater_ua_fun.base.BaseViewBindingFragment
import com.example.weater_ua_fun.databinding.FragmentCurrentDayBinding
import com.example.weater_ua_fun.model.api.responses.Current
import com.example.weater_ua_fun.model.api.responses.Forecast
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class CurrentDayFragment :  BaseViewBindingFragment<FragmentCurrentDayBinding>() {

    companion object {
        fun newInstance() = CurrentDayFragment()
    }

    private  val viewModel:CurrentDayViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_current_day, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCurrent()
        binding.buttonUpdate.setOnClickListener() {
            viewModel.getCurrent()
        }
        Log.e(":initial","CurrentDayFragment")
        lifecycleScope.launchWhenStarted {
            viewModel.forecast
                .onEach {
                    load(it)
                }
                .launchIn(lifecycleScope)
        }
    }
    private suspend fun load(forecast: Forecast) {

        forecast.location?.country.let { country->
            forecast.location?.name.let { name->
                binding.tvCity.text = "$country, $name"
            }
        }

        forecast.current?.condition?.let {
            Picasso.get().load("https:${it.icon}").into(binding.ivIcon)
            binding.tvIconTitle.text=it.text
        }

        Log.d("getCurrents1","airQuality1: \n${ forecast}")
        Log.d("getCurrents2","airQuality2: \n${ forecast.airQuality}")
        forecast.airQuality?.let {

            Log.d("getCurrents3","airQuality3: \n${ forecast.airQuality}")
            Log.d("getCurrents4","airQuality4: \n${ it}")
            when(it.us_epa_index){
               1-> binding.tvAirValue.text=getString(R.string.good)
               2-> binding.tvAirValue.text=getString(R.string.moderate)
               3-> binding.tvAirValue.text=getString(R.string.unhealthy_)
               4-> binding.tvAirValue.text=getString(R.string.unhealthy)
               5-> binding.tvAirValue.text=getString(R.string.very_unhealthy)
               6-> binding.tvAirValue.text=getString(R.string.hazardous)
            }
        }

        forecast.current?.let { _forecast ->
            _forecast.air_quality?.let {
                when(it.us_epa_index){
                    1-> binding.tvAirValue.text=getString(R.string.good)
                    2-> binding.tvAirValue.text=getString(R.string.moderate)
                    3-> binding.tvAirValue.text=getString(R.string.unhealthy_)
                    4-> binding.tvAirValue.text=getString(R.string.unhealthy)
                    5-> binding.tvAirValue.text=getString(R.string.very_unhealthy)
                    6-> binding.tvAirValue.text=getString(R.string.hazardous)
                }
            }
            viewModel.getTemperature().let {
                it?.let {
                    when(it){
                        0->{
                            binding.tvDegree.text=_forecast.temp_c.toString()
                            binding.tvDegreeG.text="c"

                            binding.tvFeelsValue.text=_forecast.feelslike_c.toString()
                            binding.tvFeelsO.text="c"
                        }
                        1->{
                            binding.tvDegree.text=_forecast.temp_f.toString()
                            binding.tvDegreeG.text="f"

                            binding.tvFeelsValue.text=_forecast.feelslike_f.toString()
                            binding.tvFeelsO.text="f"
                        }
                        else ->{

                        }
                    }
                }
            }
            viewModel.getVisibleMiles().let {
                it?.let {
                    it?.let {
                        Log.e("sssit", " "+it)
                        when(it){
                            0->{
                                Log.e("sssit", "se0"+it)
                                binding.tvWindGustValue.text=_forecast.gust_kph.toString()
                                binding.tvWindGustValueT.text="km/h"


                                binding.tvWindSpeedValue.text=_forecast.wind_kph.toString()
                                binding.tvWindSpeedValueT.text="km/h"
                            }
                            1->{
                                Log.e("sssit", "se1"+it)
                                binding.tvWindGustValue.text=_forecast.gust_mph.toString()
                                binding.tvWindGustValueT.text="m/h"

                                binding.tvWindSpeedValue.text=_forecast.wind_mph.toString()
                                binding.tvWindSpeedValueT.text="m/h"
                            }
                            else ->{
                                Log.e("sssit", "se5"+it)
                                binding.tvWindGustValue.text=_forecast.gust_kph.toString()
                                binding.tvWindGustValueT.text="km/h"

                                binding.tvWindSpeedValue.text=_forecast.wind_kph.toString()
                                binding.tvWindSpeedValueT.text="km/h"
                            }
                        }
                    }
                }
            }

            viewModel.getPressure().let {
                it?.let {
                        when(it){
                            0->{
                                binding.tvPressureValue.text=_forecast.pressure_mb.toString() +"mb"
                            }
                            1->{
                                binding.tvPressureValue.text=_forecast.pressure_in.toString() +"in"
                            }
                            else ->{
                                binding.tvPressureValue.text=_forecast.pressure_mb.toString() +"mb"
                            }
                        }
                }
            }

            viewModel.getPrecipitation().let {
                it?.let {
                        when(it){
                            0->{
                                binding.tvPrecipitationValue.text=_forecast.precip_mm.toString() +"mm"
                            }
                            1->{
                                binding.tvPrecipitationValue.text=_forecast.precip_in.toString() +"in"
                            }
                            else ->{
                                binding.tvPrecipitationValue.text=_forecast.precip_mm.toString() +"mb"
                            }
                        }
                }
            }

            viewModel.getVisibleMiles().let {
                it?.let {
                    it?.let {
                        Log.e("sssit", " "+it)
                        when(it){
                            0->{
                                Log.e("sssit", "se0"+it)
                                binding.tvWindGustValue.text=_forecast.gust_kph.toString()
                                binding.tvWindGustValueT.text="km/h"


                                binding.tvWindSpeedValue.text=_forecast.wind_kph.toString()
                                binding.tvWindSpeedValueT.text="km/h"
                            }
                            1->{
                                Log.e("sssit", "se1"+it)
                                binding.tvWindGustValue.text=_forecast.gust_mph.toString()
                                binding.tvWindGustValueT.text="m/h"

                                binding.tvWindSpeedValue.text=_forecast.wind_mph.toString()
                                binding.tvWindSpeedValueT.text="m/h"
                            }
                            else ->{
                                Log.e("sssit", "se5"+it)
                                binding.tvWindGustValue.text=_forecast.gust_kph.toString()
                                binding.tvWindGustValueT.text="km/h"

                                binding.tvWindSpeedValue.text=_forecast.wind_kph.toString()
                                binding.tvWindSpeedValueT.text="km/h"
                            }
                        }
                    }
                }
            }





            binding.tvWindDegreeValue.text=_forecast.wind_dir
            binding.tvHumidityValue.text=_forecast.humidity.toString()+"%"
            binding.tvCloudValue.text=_forecast.cloud.toString()+"%"
            binding.tvLastDt.text=_forecast.last_updated.toString()


            binding.ibUvTitleHelp.setOnClickListener{
               if(binding.tvUvTitleHelpText.isVisible){
                   if(resources.configuration.isNightModeActive){
                       binding.ibUvTitleHelp.setImageResource(R.drawable.ic_baseline_arrow_drop_up_black_mode_24)
                   }else{
                       binding.ibUvTitleHelp.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24)
                       //binding.ibUvTitleHelp.rotation= 50.0F
                   }
                    binding.tvUvTitleHelpText.visibility=INVISIBLE
               }else {
                   if(resources.configuration.isNightModeActive){
                       binding.ibUvTitleHelp.setImageResource(R.drawable.ic_baseline_arrow_drop_down_black_mode_24)
                   }else{
                       binding.ibUvTitleHelp.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24)
                   }
                   binding.tvUvTitleHelpText.visibility = VISIBLE
               }
            }
            binding.tvUvValue.text=_forecast.uv.toString()
            when(_forecast.uv){
                in 1.0..2.9->{
                    binding.tvUvValue.setTextColor(Color.GREEN)
                    binding.buttonUvValueColor.setBackgroundColor(Color.GREEN)
                    binding.tvUvTitleHelp.text=getString(R.string.uv_low)
                    binding.tvUvTitleHelp.setTextColor(Color.GREEN)
                }
                in  3.0..5.9->{
                    binding.tvUvValue.setTextColor(Color.YELLOW)
                    binding.buttonUvValueColor.setBackgroundColor(Color.YELLOW)
                    binding.tvUvTitleHelp.text=getString(R.string.uv_medium)
                    binding.tvUvTitleHelp.setTextColor(Color.YELLOW)
                }
                in 6.0..7.9->{
                    binding.tvUvValue.setTextColor(Color.parseColor("#FF1800"))
                    binding.buttonUvValueColor.setBackgroundColor(Color.parseColor("#FF1800"))
                    binding.tvUvTitleHelp.text=getString(R.string.uv_very_high)
                    binding.tvUvTitleHelp.setTextColor(Color.parseColor("#FF1800"))
                }
                in 8.0..10.9->{
                    binding.tvUvValue.setTextColor(Color.RED)
                    binding.buttonUvValueColor.setBackgroundColor(Color.RED)
                    binding.tvUvTitleHelp.text=getString(R.string.uv_very_high)
                    binding.tvUvTitleHelp.setTextColor(Color.RED)
                }
                in 11.0..15.0->{
                    binding.tvUvValue.setTextColor(Color.parseColor("#CD0074"))
                    binding.buttonUvValueColor.setBackgroundColor(Color.parseColor("#CD0074"))
                    binding.tvUvTitleHelp.text=getString(R.string.uv_extreme)
                    binding.tvUvTitleHelp.setTextColor(Color.parseColor("#CD0074"))
                }else->{

                }
            }
        }
    }
    override fun initViewBinding(view: View): FragmentCurrentDayBinding {
        return FragmentCurrentDayBinding.bind(view)
    }

}