package com.academy.alfagiftmini.presentation.homepage.components.adapter.productcategories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.academy.alfagiftmini.databinding.ItemProductCategoriesBinding
import com.academy.alfagiftmini.domain.productcategories.model.ProductCategoriesDomainModel
import com.bumptech.glide.Glide

class CategoriesAdapter (private val listener: setOnItemClicked): PagingDataAdapter<ProductCategoriesDomainModel, CategoriesAdapter.ProductCategoriesViewHolder>(DiffCallback) {
    companion object {
        object DiffCallback : DiffUtil.ItemCallback<ProductCategoriesDomainModel>() {
            override fun areItemsTheSame(
                oldItem: ProductCategoriesDomainModel,
                newItem: ProductCategoriesDomainModel
            ): Boolean {
                return (oldItem.id == newItem.id)
            }

            override fun areContentsTheSame(
                oldItem: ProductCategoriesDomainModel,
                newItem: ProductCategoriesDomainModel
            ) = oldItem == newItem
        }
    }

    interface setOnItemClicked {
        fun onCategoryClicked(position: Int)
    }

    class ProductCategoriesViewHolder(val binding: ItemProductCategoriesBinding): ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ProductCategoriesViewHolder, position: Int) {
        val data = getItem(position) ?: return
        holder.binding.apply {
            Glide.with(holder.itemView).load(data.image).into(ivCategoryImage)
            tvTitle.text = data.text
        }
        holder.binding.root.setOnClickListener {
            listener.onCategoryClicked(position)
        }
    }

    fun getItemObject(position: Int): ProductCategoriesDomainModel{
        return getItem(position) ?: ProductCategoriesDomainModel(-1, "", "", listOf())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCategoriesViewHolder {
        return ProductCategoriesViewHolder(ItemProductCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}