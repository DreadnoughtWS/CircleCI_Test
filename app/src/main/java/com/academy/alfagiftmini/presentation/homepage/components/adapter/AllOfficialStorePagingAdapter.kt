package com.academy.alfagiftmini.presentation.homepage.components.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ItemOfficialStoreBinding
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import com.bumptech.glide.Glide

class AllOfficialStorePagingAdapter :
    PagingDataAdapter<OfficialStoreDomainItemModel, AllOfficialStorePagingAdapter.DetailOfficialStoreViewHolder>(
        DiffCallback
    ) {

    companion object {
        object DiffCallback : DiffUtil.ItemCallback<OfficialStoreDomainItemModel>() {
            override fun areItemsTheSame(
                oldItem: OfficialStoreDomainItemModel, newItem: OfficialStoreDomainItemModel
            ): Boolean {
                return (oldItem.id == newItem.id)
            }

            override fun areContentsTheSame(
                oldItem: OfficialStoreDomainItemModel, newItem: OfficialStoreDomainItemModel
            ): Boolean {
                return (oldItem == newItem)
            }

        }
    }

    class DetailOfficialStoreViewHolder(private val binding: ItemOfficialStoreBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: OfficialStoreDomainItemModel) {
            println("DATA")
            println(data)
            with(binding) {
                tvNamaStore.text = data.name
            }
            Glide.with(itemView.context).load(data.image).placeholder(R.drawable.uniliver_logo)
                .into(binding.ivStoreLogo)
            Glide.with(itemView.context).load(data.productImage)
                .placeholder(R.drawable.uniliver_logo).into(binding.ivProductLogo)
        }
    }

    override fun onBindViewHolder(holder: DetailOfficialStoreViewHolder, position: Int) {
        holder.bindData(getItem(position) ?: return)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): DetailOfficialStoreViewHolder {
        val binding = ItemOfficialStoreBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DetailOfficialStoreViewHolder(binding)
    }


}