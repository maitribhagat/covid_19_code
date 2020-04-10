package com.example.e_commerce.ui.main.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class CommonGenericRecyclerViewAdapter<T, D>(
    val context: Context,
    private var mArrayList: MutableList<T>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    abstract val layoutResId: Int

    abstract fun onBindData(model: T, position: Int, dataBinding: D)

    abstract fun onItemClick(model: T, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val dataBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layoutResId,
            parent,
            false
        )
        return ItemViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CommonGenericRecyclerViewAdapter<*, *>.ItemViewHolder).mDataBinding as D
        /*onBindData(
            mArrayList[position], position,
            (holder as CommonGenericRecyclerViewAdapter<*, *>.ItemViewHolder).mDataBinding as D
        )*/

        (holder.mDataBinding as ViewDataBinding).root.setOnClickListener {
            onItemClick(
                mArrayList!!.get(position),
                position
            )
        }
    }

    override fun getItemCount(): Int {
//        return mArrayList.size
        return 10
    }

    fun addItems(arrayList: MutableList<T>) {
        mArrayList = arrayList
        this.notifyDataSetChanged()
    }

    fun getItem(position: Int): T {
        return mArrayList[position]
    }

    fun getItems(): MutableList<T> {
        return mArrayList
    }

    internal inner class ItemViewHolder(binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var mDataBinding: D = binding as D
    }

    fun notifyMyAdapter() {
        this.notifyDataSetChanged()
    }
}