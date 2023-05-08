package com.academy.alfagiftmini.presentation.homepage.components.adapter.pesanan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ItemSubmenuPesananBinding

//class AdapterSubMenuPesanan(private val subMenuPesanan: MutableList<String>) :
//    RecyclerView.Adapter<AdapterSubMenuPesanan.SubmenuPesananViewHolder>() {
//    class SubmenuPesananViewHolder(val binding: ItemSubmenuPesananBinding) :
//        ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubmenuPesananViewHolder {
//        return SubmenuPesananViewHolder(
//            ItemSubmenuPesananBinding.inflate(
//                LayoutInflater.from(parent.context), parent, false
//            )
//        )
//    }
//
//    override fun getItemCount(): Int {
//        return subMenuPesanan.size
//    }
//
//    override fun onBindViewHolder(holder: SubmenuPesananViewHolder, position: Int) {
//        val data = subMenuPesanan[position]
//        holder.binding.apply {
//            tvSubmenu.text = data
//        }
//        if(position == 1){
//            holder.binding.tvSubmenu.
//        }
//    }
//}