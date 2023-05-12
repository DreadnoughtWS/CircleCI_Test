package com.academy.alfagiftmini.presentation.homepage.components.adapter.productdetail

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ItemProdukGratisPromoBinding
import com.academy.alfagiftmini.domain.productdetail.model.ProductDetailDomainModel
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.bumptech.glide.Glide

class ProductGratisAdapter(private val productData: MutableList<ProductDetailDomainModel>): RecyclerView.Adapter<ProductGratisAdapter.GratisViewHolder>() {
    lateinit var context: Context
    class GratisViewHolder(val binding: ItemProdukGratisPromoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: ProductDetailDomainModel, context: Context) {
            binding.apply {
                when (data.productPickupAvailability) {
                    0 -> {
                        tvStokFrom.text = context.getString(R.string.stok_gudang)
                        tvStokFrom.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            R.drawable.stok_gudang,
                            0,
                            0,
                            0
                        )
                    }
                    1 -> {
                        tvStokFrom.text = context.getString(R.string.stok_toko)
                        tvStokFrom.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            R.drawable.stok_toko,
                            0,
                            0,
                            0
                        )
                    }
                }
                tvHargaProductAsli.text = PresentationUtils.formatter(data.price)
                tvHargaProductAsli.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                tvProductName.text = data.productName

                Glide.with(itemView)
                    .load(data.productImages[0].url[0])
                    .into(ivGambarProdukDiskon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GratisViewHolder {
        val binding = ItemProdukGratisPromoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        context = parent.context

        return GratisViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productData.size
    }

    override fun onBindViewHolder(holder: GratisViewHolder, position: Int) {
        holder.bindData(productData[position],context)
    }

    fun addDataToList(productDetailDomainModel: ProductDetailDomainModel) {
        productData.add(productDetailDomainModel)
        notifyItemRangeChanged(0,productData.size)
    }
}