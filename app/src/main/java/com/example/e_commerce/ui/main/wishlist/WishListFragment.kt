package com.example.e_commerce.ui.main.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.R
import com.example.e_commerce.databinding.FragmentRecyclerviewBinding
import com.example.e_commerce.databinding.WishlistAdapterBinding
import com.example.e_commerce.ui.main.base.CommonGenericRecyclerViewAdapter

class WishListFragment : Fragment() {

    private lateinit var binding: FragmentRecyclerviewBinding
    private lateinit var viewModel: WishListViewModel
    private lateinit var wishlistAdapter: CommonGenericRecyclerViewAdapter<*, *>
    private lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_recyclerview, container, false)
        binding.lifecycleOwner = this

        viewModel = ViewModelProviders.of(activity!!).get(WishListViewModel::class.java)
        binding.viewmodel = viewModel

        linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.commonRecyclerview.layoutManager = linearLayoutManager

        wishlistAdapter = object : CommonGenericRecyclerViewAdapter<String, WishlistAdapterBinding>(
            context!!, mutableListOf()
        ) {
            override val layoutResId: Int
                get() = R.layout.wishlist_adapter

            override fun onBindData(
                model: String,
                position: Int,
                dataBinding: WishlistAdapterBinding
            ) {

            }

            override fun onItemClick(model: String, position: Int) {
            }
        }

        binding!!.commonRecyclerview.adapter = wishlistAdapter

        return binding.root
    }
}