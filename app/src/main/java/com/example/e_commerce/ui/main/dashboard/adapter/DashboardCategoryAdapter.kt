package com.example.e_commerce.ui.main.dashboard.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.R
import com.example.e_commerce.databinding.CovidDashboardCategoryAdapterBinding
import com.example.e_commerce.ui.main.dashboard.model.Category


class DashboardCategoryAdapter(val categoryList: MutableList<Category>?, val activity: Context?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var binding: CovidDashboardCategoryAdapterBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.covid_dashboard_category_adapter, parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList!!.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val categoryItem = categoryList!!.get(position)
        binding!!.categoryData = categoryItem
        binding!!.executePendingBindings()
    }


    class ViewHolder(val view: CovidDashboardCategoryAdapterBinding?) :
        RecyclerView.ViewHolder(view!!.root) {
        // Holds the TextView that will add each animal to


    }


}

@BindingAdapter("categoryloadImage")
fun categoryloadImage(view: ImageView, img_image: String) {
    if (!img_image.isNullOrEmpty()) {
        val imgURI = Uri.parse(img_image)
        view.setImageURI(imgURI);
    } else {
        view.visibility =View.GONE

    }
}
