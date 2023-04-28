package com.academy.alfagiftmini.presentation.homepage.components.adapter.officialstore

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ItemBrandBinding
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStorebrandsDomainItemModel
import com.bumptech.glide.Glide

class BrandsAdapter : RecyclerView.Adapter<BrandsAdapter.BrandsViewHolder>() {
    private val listData = arrayListOf<OfficialStorebrandsDomainItemModel>()

    class BrandsViewHolder(val binding: ItemBrandBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: OfficialStorebrandsDomainItemModel) {
            with(binding) {
                tvNamaBrand.text = data.brandName
            }
            Glide.with(itemView.context).load(data.brandImage).placeholder(R.drawable.uniliver_logo)
                .into(binding.ivBrandLogo)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandsViewHolder {
        val binding =
            ItemBrandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BrandsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: BrandsViewHolder, position: Int) {
        holder.bindData(listData[position])
    }

    fun updateData(data: List<OfficialStorebrandsDomainItemModel>) {
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }


}