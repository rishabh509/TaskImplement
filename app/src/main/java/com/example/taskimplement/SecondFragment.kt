package com.example.taskimplement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.taskimplement.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {
    lateinit var binding:FragmentSecondBinding
    lateinit var adapter: DataAdapter
    lateinit var dataList: ArrayList<DataModel>
    private var id: Int? = 0
    private var userid:Int? = 0
    private var title: String? = ""
    private var body: String? = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSecondBinding.inflate(inflater, container, false)
        dataList = ArrayList()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= DataAdapter()
        id = arguments?.getInt("id")
        userid = arguments?.getInt("userid")
        body = arguments?.getString("body")
        title = arguments?.getString("title")
        dataList.add(DataModel(userid!!,id!!,title.toString(),body.toString()))
        adapter.setList(dataList)
        binding.idRVData.adapter = adapter

    }


}