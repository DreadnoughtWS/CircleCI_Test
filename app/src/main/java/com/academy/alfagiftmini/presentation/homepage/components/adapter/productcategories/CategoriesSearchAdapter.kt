package com.academy.alfagiftmini.presentation.homepage.components.adapter.productcategories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.academy.alfagiftmini.databinding.ItemKategoriProdukSearchBinding
import com.academy.alfagiftmini.domain.productcategories.model.ProductCategoriesDomainModel
import com.bumptech.glide.Glide

class CategoriesSearchAdapter: PagingDataAdapter<ProductCategoriesDomainModel, CategoriesSearchAdapter.CategoriesViewHolder>(DiffCallback) {
    lateinit var listener: SetOnCategoryClick
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
    
    
    interface SetOnCategoryClick {
        fun onCategoryClicked(position: Int)
    }
    
    class CategoriesViewHolder(val binding: ItemKategoriProdukSearchBinding): RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val data = getItem(position) ?: return
        holder.binding.apply {
            Glide.with(root).load(data.image).into(ivGambarKategori)
            tvKategoriProduk.text = data.text

            root.setOnClickListener {
                listener.onCategoryClicked(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesSearchAdapter.CategoriesViewHolder {
        return CategoriesSearchAdapter.CategoriesViewHolder(
            ItemKategoriProdukSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun getItemObject(position: Int): ProductCategoriesDomainModel{
        return getItem(position) ?: ProductCategoriesDomainModel(-1, "", "", listOf())
    }
}