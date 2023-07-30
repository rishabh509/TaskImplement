package com.example.taskimplement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.taskimplement.databinding.ItemLayoutBinding

class DataAdapter :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    var itemList = mutableListOf<DataModel>()
    lateinit var onItemClickListener: setOnItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemModel = itemList[position]
        holder.setData(itemModel, position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setList(item: MutableList<DataModel>) {
//        itemList.clear()
        itemList.addAll(item)
        notifyDataSetChanged()
    }

    inner class ViewHolder(Item: View) : RecyclerView.ViewHolder(Item) {
        var dataBinding: ItemLayoutBinding = DataBindingUtil.bind(Item.rootView)!!
        fun setData(itemModel: DataModel, position: Int) {
            dataBinding.txtId.text=itemModel.id.toString()
            dataBinding.txtUserId.text = itemModel.userId.toString()
            dataBinding.txtBody.text = itemModel.body
            dataBinding.txtTitle.text = itemModel.title
            dataBinding.mainlayout.setOnClickListener {
                onItemClickListener.onItemClick(itemModel)
            }



        }
    }

    interface setOnItemClickListener{
        fun onItemClick(item:DataModel)



    }
    fun setOnClickItemClick(onItemClickListener: setOnItemClickListener){
        this.onItemClickListener=onItemClickListener

    }
}