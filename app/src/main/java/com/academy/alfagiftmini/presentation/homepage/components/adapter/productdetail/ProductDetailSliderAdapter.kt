package com.academy.alfagiftmini.presentation.homepage.components.adapter.productdetail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.academy.alfagiftmini.databinding.ItemProductDetailSliderBinding
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter

class ProductDetailSliderAdapter(private var listImage: List<String>, var context: Context) :
    SliderViewAdapter<ProductDetailSliderAdapter.SliderImageAdapterViewHolder>() {


    class SliderImageAdapterViewHolder(val binding: ItemProductDetailSliderBinding) : ViewHolder(binding.root) {

        fun bindImage(imageUrl: String) {
            with(binding){
                Glide.with(itemView.context)
                    .load(imageUrl)
                    .into(ivImageSliderDetail)
            }
        }

    }

    override fun getCount(): Int {
        return listImage.size
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderImageAdapterViewHolder {
        val binding = ItemProductDetailSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return SliderImageAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: SliderImageAdapterViewHolder, position: Int) {

        viewHolder.bindImage(listImage[position])
    }
}