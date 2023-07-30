package com.example.taskimplement

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.taskimplement.databinding.FragmentFirstBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class FirstFragment : Fragment() {
    lateinit var binding:FragmentFirstBinding
    lateinit var adapter: DataAdapter
    lateinit var dataList: ArrayList<DataModel>
    private lateinit var communicator: Communicator
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFirstBinding.inflate(inflater, container, false)
        dataList = ArrayList()
        getAllData()
        communicator = activity as Communicator
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = DataAdapter()
        adapter.setOnClickItemClick(object:DataAdapter.setOnItemClickListener{
            override fun onItemClick(item: DataModel) {
//                dataList.add(DataModel(item.id,item.userId,item.title,item.body))
//                adapter = DataAdapter()

//
                communicator.passData(item.id,item.userId,item.title,item.body)



            }

        })
    }

    private fun getAllData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")

            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val retrofitAPI = retrofit.create(RetrofitAPI::class.java)


        val call: Call<ArrayList<DataModel>?>? = retrofitAPI.getAllData()

        call!!.enqueue(object : Callback<ArrayList<DataModel>?> {
            override fun onResponse(
                call: Call<ArrayList<DataModel>?>,
                response: Response<ArrayList<DataModel>?>
            ) {
                if (response.isSuccessful) {
                    dataList = response.body()!!
                }

                // on below line we are initializing our adapter.

                adapter.setList(dataList)

                binding.idRVData.adapter = adapter

            }

            override fun onFailure(call: Call<ArrayList<DataModel>?>, t: Throwable) {

//                Toast.makeText(this@MainActivity, "Fail to get the data..", Toast.LENGTH_SHORT)
//                    .show()
            }
        })
    }

}