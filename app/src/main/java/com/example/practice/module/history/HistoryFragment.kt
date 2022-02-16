package com.example.practice.module.history

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.R
import com.example.practice.base.BaseFragment
import com.example.practice.bean.Data
import com.example.practice.bean.HistoryBean
import com.example.practice.databinding.FragmentHistoryBinding
import com.example.practice.utils.LoadingDialogUtils
import java.util.*
import kotlin.collections.ArrayList


class HistoryFragment : BaseFragment<FragmentHistoryBinding>(FragmentHistoryBinding::inflate) {

    private lateinit var historyViewModel: HistoryViewModel
    private var startDate = "2021年12月1日"
    private var endDate = "2021年12月31日"
    private var mLoadingDialog: Dialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }

    private fun initData() {
        historyViewModel =
            ViewModelProvider(this)[HistoryViewModel::class.java]
        getActivity()?.let { historyViewModel.getHistoryList(startDate,endDate) }
    }

    private fun initView() {
        val historyListView: RecyclerView = viewBinding.listView
        val graphView: ImageView = viewBinding.titleGraph.titleHistoryShowGraph
        val endTv: TextView = viewBinding.titleGraph.dateEnd
        val startTv: TextView = viewBinding.titleGraph.dateStart
        var loadingDialog = LoadingDialogUtils()

        startTv.text = startDate
        endTv.text = endDate
        graphView.setImageResource(R.mipmap.pie_chart)
        historyViewModel.historyListLiveData.observe(viewLifecycleOwner, Observer {
            var init: (View, Data) -> Unit = { v:View, d:Data ->
                var addressView = v.findViewById<TextView>(R.id.address)
                var dateview=v.findViewById<TextView>(R.id.time)
                var priceView=v.findViewById<TextView>(R.id.price)
                addressView.text = d.address
                dateview.text = d.date
                priceView.text = d.price
            }
            //get from networkApi
            var adapter = it.getOrNull()?.let { it1 ->
                HistoryListViewAdapter(
                    R.layout.history_list_item,
                    it1.dataList,init)
            }

            historyListView.layoutManager= LinearLayoutManager(getActivity())
            historyListView.adapter=adapter

        })
        historyViewModel.loadingLiveData.observe(viewLifecycleOwner,{
            if(it){
                mLoadingDialog = loadingDialog.createLoadingDialog(getActivity(),"Loading")
            } else {
                loadingDialog.closeDialog(mLoadingDialog)
            }
        })
        //グラフ画面へ遷移
        graphView.setOnClickListener{
            val bundle = Bundle()
            val arrayList = ArrayList<Data>()
            var data = Data("ルミネ新宿","30000円","2021/01/02")
            var data1 = Data("ルミネ新宿","30000円","2021/01/02")
            var data2 = Data("アトレ吉祥寺","30000円","2021/01/02")
            var data3 = Data("アトレ吉祥寺","30000円","2021/01/02")
            var data4 = Data("○○〇","30000円","2021/01/02")
            var data5 = Data("○○〇","30000円","2021/01/02")
            var data6 = Data("○○〇","30000円","2021/01/02")
            var data7 = Data("ルミネ新宿","30000円","2021/01/02")

            var history= HistoryBean(listOf(data,data1,data2,data3,data4,data5,data6,data7))

            val action=HistoryFragmentDirections.actionNavigationHistoryToNavigationHistoryGraph(history)

            findNavController().navigate(action)
        }
        //startDateを選択
        startTv.setOnClickListener {
            showDatePickerDialog(startTv)
        }
        //endDateを選択
        endTv.setOnClickListener {
            showDatePickerDialog(endTv)
        }
    }

    private fun showDatePickerDialog(tv:TextView) {
        val ca = Calendar.getInstance()
        var mYear = ca[Calendar.YEAR]
        var mMonth = ca[Calendar.MONTH]
        var mDay = ca[Calendar.DAY_OF_MONTH]

        val datePickerDialog = context?.let {
            DatePickerDialog(
                it,
                { _, year, month, dayOfMonth ->
                    mYear = year
                    mMonth = month
                    mDay = dayOfMonth
                    val mDate = "${year}年${month + 1}月${dayOfMonth}日"
                    tv.text = mDate
                },
                mYear, mMonth, mDay
            )
        }
        datePickerDialog?.show()
    }
}