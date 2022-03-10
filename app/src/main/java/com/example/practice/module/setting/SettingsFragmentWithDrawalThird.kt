package com.example.practice.module.setting

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.practice.R
import com.example.practice.base.BaseFragment
import com.example.practice.databinding.FragmentPayBinding
import com.example.practice.databinding.FragmentSettingsBinding
import com.example.practice.module.pay.PayViewModel
import androidx.navigation.fragment.findNavController
import com.example.practice.databinding.FragmentSettingsWithdrawalBinding
import com.example.practice.databinding.FragmentSettingsWithdrawalThirdBinding

class SettingsFragmentWithDrawalThird : BaseFragment<FragmentSettingsWithdrawalThirdBinding>(
    FragmentSettingsWithdrawalThirdBinding::inflate){

//    val DeleteAccount : Button = viewBinding.DeleteAccount

    private lateinit var settingViewModel: SettingsViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        //   initView()

    }
    private fun initData() {

        settingViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)


    }


}