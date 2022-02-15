package com.example.practice.module.pay

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.practice.base.BaseFragment
import com.example.practice.bean.Data
import com.example.practice.bean.QRData
import com.example.practice.databinding.FragmentPayBinding
import com.example.practice.network.ReadJsonFile

class PayFragment : BaseFragment<FragmentPayBinding>(FragmentPayBinding::inflate) {

    private lateinit var payViewModel: PayViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()

        val test=QRData.get()
        val tet2=test

    }
    private fun initData() {
        payViewModel =
            ViewModelProvider(this)[PayViewModel::class.java]
    }

    private fun initView() {
        val textView: TextView = viewBinding.textPay
        payViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

    }


}