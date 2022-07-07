package com.example.weater_ua_fun.ui.currentdaydetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weater_ua_fun.R

class CurrentDayDetailFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentDayDetailFragment()
    }

    private lateinit var viewModel: CurrentDayDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current_day_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CurrentDayDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}