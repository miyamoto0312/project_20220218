package com.example.practice.module.pay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.practice.base.BaseFragment
import com.example.practice.databinding.FragmentAmountBinding

class AmountFragment: BaseFragment<FragmentAmountBinding>(FragmentAmountBinding::inflate) {

    private val args:AmountFragmentArgs by navArgs()

    private lateinit var payViewModel: PayViewModel

    private var _binding: FragmentAmountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentAmountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.txtDestination.text=args.qrData

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}