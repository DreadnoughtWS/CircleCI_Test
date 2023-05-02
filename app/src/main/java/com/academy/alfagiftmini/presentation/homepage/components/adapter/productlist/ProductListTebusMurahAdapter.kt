package com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ItemOfficialStoreBinding
import com.academy.alfagiftmini.databinding.ItemTebusMurahBinding
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListTebusMurahDomainModel
import com.academy.alfagiftmini.presentation.homepage.components.adapter.officialstore.OfficialStore14Adapter
import com.bumptech.glide.Glide

class ProductListTebusMurahAdapter :
    RecyclerView.Adapter<ProductListTebusMurahAdapter.TebusMurahViewHolder>() {
    private var adapter = ProductListTebusMurahProductAdapter()
    private val listData = arrayListOf<ProductListTebusMurahDomainModel>()
    private var context: Context? = null

    class TebusMurahViewHolder(val binding: ItemTebusMurahBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: ProductListTebusMurahDomainModel) {
            with(binding) {
                tvAccordionName.text = data.accordionName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TebusMurahViewHolder {
        adapter = ProductListTebusMurahProductAdapter()
        context = parent.context
        val binding =
            ItemTebusMurahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TebusMurahViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: TebusMurahViewHolder, position: Int) {
        holder.bindData(listData[position])
        holder.binding.rvProductListTebusMurah.adapter = adapter
        holder.binding.rvProductListTebusMurah.layoutManager = LinearLayoutManager(context)
        adapter.updateData(listData[position].tebusMurahProduct)

        holder.binding.llTebusMurah.setOnClickListener {
            if (holder.binding.rlExpandableLayout.visibility == View.GONE) {
                holder.binding.ivArrow.setImageResource(R.drawable.baseline_keyboard_arrow_up_24_gray)
                holder.binding.rlExpandableLayout.visibility = View.VISIBLE
            } else {
                holder.binding.ivArrow.setImageResource(R.drawable.baseline_keyboard_arrow_down_24_gray)
                holder.binding.rlExpandableLayout.visibility = View.GONE
            }
        }
    }

    fun updateDaata(data: List<ProductListTebusMurahDomainModel>) {
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }
}