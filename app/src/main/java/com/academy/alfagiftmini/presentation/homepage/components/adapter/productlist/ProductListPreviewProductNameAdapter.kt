package com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist

import android.content.ClipData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.academy.alfagiftmini.databinding.ItemPreviewNameProductBinding
import com.academy.alfagiftmini.domain.produklist.model.ProductListDomainItemModel

class ProductListPreviewProductNameAdapter :
    RecyclerView.Adapter<ProductListPreviewProductNameAdapter.ProductNameViewHolder>() {
    private val listData: ArrayList<String> = arrayListOf()

    class ProductNameViewHolder(val binding: ItemPreviewNameProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductNameViewHolder {
        val binding = ItemPreviewNameProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductNameViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ProductNameViewHolder, position: Int) {
        holder.binding.tvProductName.text = listData[position]
    }

    fun updateData(data: List<ProductListDomainItemModel>) {
        listData.clear()
        data.forEach {
            listData.add(it.productName)
        }
        notifyDataSetChanged()
    }
}