package com.example.weater_ua_fun.ui.forecast

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.weater_ua_fun.R
import com.example.weater_ua_fun.base.BaseViewBindingFragment
import com.example.weater_ua_fun.databinding.FragmentCurrentDayBinding
import com.example.weater_ua_fun.databinding.FragmentForecastBinding
import com.example.weater_ua_fun.databinding.FragmentSettingsBinding
import com.example.weater_ua_fun.ui.forecast.adapter.ForecastAdapter
import com.google.android.material.tabs.TabLayoutMediator

class ForecastFragment  :  BaseViewBindingFragment<FragmentForecastBinding>()  {
    private lateinit var adapter: ForecastAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e(":initial","ForecastFragment")
        adapter= ForecastAdapter(this)
        binding.viewPager.adapter=adapter

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
           when(position){
               0->{
                   tab.text=getString(R.string.weather_today)
                   tab.setIcon(R.drawable.ic_menu_gallery )
               }
               1-> {
                   tab.text = getString(R.string.weather_detail)
                   tab.setIcon(R.drawable.ic_menu_camera )
               }
               2->{
                   tab.text=getString(R.string.by_days)
                   tab.setIcon(R.drawable.side_nav_bar )
               }
           }
        }.attach()
    }
    override fun initViewBinding(view: View): FragmentForecastBinding {
        return FragmentForecastBinding.bind(view)
    }
}