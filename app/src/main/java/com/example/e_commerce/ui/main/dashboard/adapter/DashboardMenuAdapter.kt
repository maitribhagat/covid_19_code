package com.example.e_commerce.ui.main.dashboard.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.R
import com.example.e_commerce.databinding.DashboardMenuAdapterBinding
import com.example.e_commerce.ui.main.dashboard.model.Menu
import com.example.e_commerce.ui.main.listener.NavigatationInterface


class DashboardMenuAdapter(val menuList: MutableList<Menu>?, val activity: Context?,var navigationListener : NavigatationInterface) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var binding: DashboardMenuAdapterBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.dashboard_menu_adapter, parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return menuList!!.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val menuItem = menuList!!.get(position)
        binding!!.menuData = menuItem

        binding!!.menuCardview.setOnClickListener { navigationListener.navigateTo(position,"menu") }
        binding!!.executePendingBindings()
    }


    class ViewHolder(val view: DashboardMenuAdapterBinding?) :
        RecyclerView.ViewHolder(view!!.root) {
        // Holds the TextView that will add each animal to


    }


}

@BindingAdapter("menuLoadImage")
fun menuLoadImage(view: ImageView, img_image: String) {
    if (img_image != null) {
        val imgURI = Uri.parse(img_image)
        view.setImageURI(imgURI);
        //view.setImageResource(img_image)
    } else {
//            Glide.with(getContext()).load(logoUrl).crossFade().into(view)
    }
}