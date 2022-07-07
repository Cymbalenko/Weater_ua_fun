package com.example.weater_ua_fun.ui.forecast.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weater_ua_fun.ui.currentday.CurrentDayFragment
import com.example.weater_ua_fun.ui.forecast.ForecastFragment
import com.example.weater_ua_fun.ui.gallery.GalleryFragment
import com.example.weater_ua_fun.ui.home.HomeFragment
import com.example.weater_ua_fun.ui.setting.SettingsFragment
import com.example.weater_ua_fun.ui.slideshow.SlideshowFragment

class ForecastAdapter(fragment: ForecastFragment) :FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = when(position){
            0->CurrentDayFragment()
            1->SlideshowFragment()
            2->SettingsFragment()
            else -> CurrentDayFragment()
        }
        return fragment
    }


}
