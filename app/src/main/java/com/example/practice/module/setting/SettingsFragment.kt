package com.example.practice.module.setting

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.practice.R
import com.example.practice.base.BaseFragment
import com.example.practice.databinding.FragmentPayBinding
import com.example.practice.databinding.FragmentSettingsBinding
import com.example.practice.module.pay.PayViewModel

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    private lateinit var settingViewModel: SettingsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
      //   initView()
    }
    private fun initData() {
        settingViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)
    }

  /*  private fun initView() {
        val textView: TextView = viewBinding.textSettings
        settingViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
    }
*/

}