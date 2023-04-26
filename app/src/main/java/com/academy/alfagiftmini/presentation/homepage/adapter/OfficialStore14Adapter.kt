package com.academy.alfagiftmini.presentation.homepage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ItemOfficialStoreBinding
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import com.bumptech.glide.Glide

class OfficialStore14Adapter :
    RecyclerView.Adapter<OfficialStore14Adapter.OfficialStoreViewHolder>() {
    private val listData = arrayListOf<OfficialStoreDomainItemModel>()
    private lateinit var context: Context

    class OfficialStoreViewHolder(private val binding: ItemOfficialStoreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: OfficialStoreDomainItemModel) {
            with(binding) {
                tvNamaStore.text = data.name
            }
            Glide.with(itemView.context).load(data.image).placeholder(R.drawable.uniliver_logo)
                .into(binding.ivStoreLogo)
            Glide.with(itemView.context).load(data.productImage)
                .placeholder(R.drawable.uniliver_logo).into(binding.ivProductLogo)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfficialStoreViewHolder {
        val binding =
            ItemOfficialStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return OfficialStoreViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: OfficialStoreViewHolder, position: Int) {
        holder.bindData(listData[position])
    }

    fun updateData(data: List<OfficialStoreDomainItemModel>) {
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

}