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
import com.example.practice.databinding.FragmentSettingsWithdrawalSecondBinding

class SettingsFragmentWithDrawalSecond : BaseFragment<FragmentSettingsWithdrawalSecondBinding>(FragmentSettingsWithdrawalSecondBinding::inflate){

//    val DeleteAccount : Button = viewBinding.DeleteAccount

    private lateinit var settingViewModel: SettingsViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

    }
//
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        //   initView()
        view.findViewById<Button>(R.id.btn_drawal_ok).setOnClickListener{
            findNavController().navigate(R.id.action_settings_withdrawal_second_to_settings_withdrawal_third)
        }

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