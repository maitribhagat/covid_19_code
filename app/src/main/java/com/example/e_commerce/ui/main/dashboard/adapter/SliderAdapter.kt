package com.example.e_commerce.ui.main.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.databinding.SliderAdapterBinding
import com.example.e_commerce.ui.main.dashboard.model.SliderImagesModel
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter



class SliderAdapter(var context : Context,
                    var imagesList : List<SliderImagesModel>,
                    var handler : ViewPagerClick) : RecyclerView.Adapter<SliderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SliderAdapterBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind.demomodel =imagesList[position]
        holder.bind.executePendingBindings()

        holder.bind.infoImage.setOnClickListener {
            //Toast.makeText(context!!, position.toString(), Toast.LENGTH_SHORT).show()
            handler.onClick(position)

        }
    }

    class ViewHolder(binding: SliderAdapterBinding) : RecyclerView.ViewHolder(binding.root)  {
        val bind : SliderAdapterBinding = binding
    }


    interface ViewPagerClick {
        fun onClick(pos : Int)
    }
}

/*todo write outside the class*/
@BindingAdapter("loadImage")
fun loadImage(view: ImageView, img_image : Int) {
    if (img_image != null) {
        view.setImageResource(img_image)
    } else {
//            Glide.with(getContext()).load(logoUrl).crossFade().into(view)
    }
}