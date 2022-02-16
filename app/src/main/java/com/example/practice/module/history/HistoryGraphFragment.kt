package com.example.practice.module.history

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.practice.R
import com.example.practice.base.BaseFragment
import com.example.practice.bean.CircleDataBean
import com.example.practice.bean.Data
import com.example.practice.databinding.FragmentHistoryGraphBinding
import com.example.practice.utils.CirqueStatisticalView


class HistoryGraphFragment : BaseFragment<FragmentHistoryGraphBinding>(FragmentHistoryGraphBinding::inflate) {

    private lateinit var historyViewModel: HistoryViewModel

    private val circleData = mutableListOf<CircleDataBean>()
    private var arrayList = mutableListOf<Data>()
    private val args:HistoryGraphFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dummyData()
        initView()
    }

    private fun initView(){
        val graphTitleImg: ImageView = viewBinding.graphImg.titleHistoryShowGraph

        graphTitleImg.setImageResource(R.mipmap.list)
        graphTitleImg.setOnClickListener {
            findNavController().popBackStack()
        }
        val graphView : CirqueStatisticalView = viewBinding.graphView
        graphView.setItems(circleData)
    }



    private fun dummyData(){
        var i =1

        val history= args.historyData?.dataList ?: return

        arrayList=history.toMutableList()

        val map = mutableMapOf<String, Int>()

        for (item in arrayList){
            if(map[item.address] == null){
                i =1
                map[item.address] =  i
            }else{
                var currentPrice = map[item.address]
                map[item.address] =  i + currentPrice!!
            }
        }

        var count = 0
        for (item in map){
            if(count == 0){
                circleData.add(CircleDataBean(item.value*100/arrayList.size, item.key, requireContext().getColor(R.color.light_pink)))
            }
            if(count == 1){
                circleData.add(CircleDataBean(item.value*100/arrayList.size, item.key, requireContext().getColor(R.color.light_yellow)))
            }
            if(count == 2){
                circleData.add(CircleDataBean(item.value*100/arrayList.size, item.key, requireContext().getColor(R.color.light_blue)))
            }
            if(count == 3){
                circleData.add(CircleDataBean(item.value*100/arrayList.size, item.key, requireContext().getColor(R.color.gray)))
            }
            count++
        }
    }

}