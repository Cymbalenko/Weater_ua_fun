package com.example.weater_ua_fun.ui.setting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.weater_ua_fun.R
import com.example.weater_ua_fun.base.BaseViewBindingFragment
import com.example.weater_ua_fun.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class SettingsFragment :  BaseViewBindingFragment<FragmentSettingsBinding>() {


    private  val viewModel:SettingsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun initViewBinding(view: View): FragmentSettingsBinding {
        return FragmentSettingsBinding.bind(view)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadSpinners()

        Log.e(":initial","SettingsFragment")

    }
    fun loadSpinners() {

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.reply_degrees,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            binding.spinnerReplyDegrees.adapter = adapter
            lifecycleScope.launch{
                viewModel.getTemperature()?.let {
                    binding.spinnerReplyDegrees.setSelection(it)
                }
            }
            binding.spinnerReplyDegrees.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        viewModel.setTemperature(position)
                    }
                }
        }
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.reply_unit,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            binding.spinnerReplyUnit.adapter = adapter
            lifecycleScope.launch{
                viewModel.getVisibleMiles()?.let {
                    binding.spinnerReplyUnit.setSelection(it)
                }
            }
            binding.spinnerReplyUnit.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        viewModel.setVisibleMiles(position)
                    }
                }
        }


        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.reply_precipitation_unit,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            binding.spinnerReplyPrecip.adapter = adapter
            lifecycleScope.launch{
                viewModel.getPrecipitationUnit()?.let {
                    binding.spinnerReplyPrecip.setSelection(it)
                }
            }
            binding.spinnerReplyPrecip.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        viewModel.setPrecipitationUnit(position)
                    }
                }
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.reply_pressure_unit,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            binding.spinnerReplyPressure.adapter = adapter
            lifecycleScope.launch{
                viewModel.getPressure()?.let {
                    binding.spinnerReplyPressure.setSelection(it)
                }
            }
            binding.spinnerReplyPressure.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        viewModel.setPressure(position)
                    }
                }
        }
    }
}