package com.academy.alfagiftmini.presentation.homepage.components.adapter.riwayatpencarian

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.academy.alfagiftmini.databinding.ItemRiwayatPencarianBinding
import com.academy.alfagiftmini.domain.riwayatpencarian.model.RiwayatPencarianDomainModel

class RiwayatPencarianAdapter(private val histori: MutableList<RiwayatPencarianDomainModel>): Adapter<RiwayatPencarianAdapter.RiwayatViewHolder>() {
    lateinit var listener: SetOnRiwayatClickListener

    class RiwayatViewHolder(val binding: ItemRiwayatPencarianBinding): ViewHolder(binding.root)

    interface SetOnRiwayatClickListener {
        fun onItemClicked(text: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatViewHolder {
        return RiwayatViewHolder(ItemRiwayatPencarianBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return histori.size
    }

    override fun onBindViewHolder(holder: RiwayatViewHolder, position: Int) {
        val data = histori[position]
        holder.binding.apply {
            tvRiwayatPencarian.text = data.text
            root.setOnClickListener {
                listener.onItemClicked(data.text ?: "")
            }
        }
    }

    fun updateList(newList: List<RiwayatPencarianDomainModel>) {
        histori.clear()
        histori.addAll(newList)
        notifyDataSetChanged()
    }
}