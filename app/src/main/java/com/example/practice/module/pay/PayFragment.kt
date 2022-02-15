package com.example.practice.module.pay

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.practice.R
import com.example.practice.base.BaseFragment
import com.example.practice.databinding.FragmentHomeBinding
import com.example.practice.databinding.FragmentNotificationsBinding
import com.example.practice.databinding.FragmentPayBinding
import com.example.practice.module.ScanQRActivity
import com.example.practice.module.home.HomeViewModel
import com.example.practice.module.notifications.NotificationsViewModel
import android.app.Activity
import android.app.Activity.RESULT_OK
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import com.google.zxing.client.android.Intents


class PayFragment : BaseFragment<FragmentPayBinding>(FragmentPayBinding::inflate) {

    private lateinit var payViewModel: PayViewModel

    private var _binding: FragmentPayBinding? = null
    private val binding get() = _binding!!

    private lateinit var gdata:String

    private val _qrlauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult(),ActivityResultCallbackFromQR())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = FragmentPayBinding.inflate(inflater, container, false)
        val root: View = binding.root
        // 同じActivityのFragmentから別のFragmentへ遷移
        binding.btnTest.setOnClickListener{
            findNavController().navigate(R.id.navigation_amount)
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val intent = Intent(this.activity, ScanQRActivity().javaClass)
        startActivityForResult(intent,1201)*/
        _qrlauncher.launch(Intent(this.activity,ScanQRActivity().javaClass))
    }



    private inner class ActivityResultCallbackFromQR : ActivityResultCallback<ActivityResult> {
        override fun onActivityResult(result: ActivityResult?) {
            if(result?.resultCode == RESULT_OK) {
                var data= result.data?.getStringExtra("qrdata").toString()
                //var bundle = bundleOf("qrdate" to data?.getStringExtra("qrdata").toString())
                //findNavController().navigate(R.id.navigation_amount,bundle)
                var action=PayFragmentDirections.actionNavigationPayToNavigationAmount(data)
                findNavController().navigate(action)
            }
            else{
                findNavController().popBackStack()
            }
        }
    }


}