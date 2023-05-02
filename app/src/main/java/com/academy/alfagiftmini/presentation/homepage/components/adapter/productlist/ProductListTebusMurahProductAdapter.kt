package com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ItemProductTebusMurahBinding
import com.academy.alfagiftmini.databinding.ItemTebusMurahBinding
import com.academy.alfagiftmini.domain.produklist.model.ProductListDetailTebusMurahDomainModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListTebusMurahDomainModel
import com.academy.alfagiftmini.presentation.PresentationUtils.hargaFormatter
import com.bumptech.glide.Glide

class ProductListTebusMurahProductAdapter :
    RecyclerView.Adapter<ProductListTebusMurahProductAdapter.ProductTebusMurahViewHolder>() {

    private val listData = arrayListOf<ProductListDetailTebusMurahDomainModel>()

    class ProductTebusMurahViewHolder(val binding: ItemProductTebusMurahBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: ProductListDetailTebusMurahDomainModel) {
            with(binding){
                tvNamaProduct.text = data.productName

                if(data.productSpecialPrice == null || data.productSpecialPrice == 0){
                    showSpecialPriceNull(data)
                    showTvStockFrom(data)
                    showImageProduct(data)
                    return
                }
                if (data.productSpecialPrice < data.price) {
                    tvHargaProductDiskon.text = "Rp. ${hargaFormatter(data.price)}"
                    tvHargaProductDiskon.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

                    tvHargaProduct.text = "Rp. ${hargaFormatter(data.productSpecialPrice)}"

                } else {
                    tvHargaProductDiskon.visibility = View.GONE
                    tvHargaProduct.text = "Rp. ${hargaFormatter(data.price)}"
                }



                showTvStockFrom(data)
                showImageProduct(data)
            }
        }

        private fun showImageProduct(data: ProductListDetailTebusMurahDomainModel) {
            Glide.with(itemView.context).load(data.productImages[0].url[0])
                .placeholder(R.drawable.uniliver_logo).into(binding.ivImage)
        }

        private fun showTvStockFrom(data: ProductListDetailTebusMurahDomainModel) {
            with(binding) {
                if (data.productPickupAvailability == 1) {
                    tvStockFrom.text = itemView.context.getString(R.string.stock_dari_toko)
                    tvStockFrom.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.stok_toko, 0, 0, 0
                    )
                } else {
                    tvStockFrom.text = itemView.context.getString(R.string.stock_dari_gudang)
                    tvStockFrom.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.stok_gudang, 0, 0, 0
                    )
                }


            }
        }

        private fun showSpecialPriceNull(data: ProductListDetailTebusMurahDomainModel) {
            with(binding) {
                tvHargaProductDiskon.visibility = View.GONE
                tvHargaProduct.text = "Rp. ${hargaFormatter(data.price)}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductTebusMurahViewHolder {
        val binding =
            ItemProductTebusMurahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductTebusMurahViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ProductTebusMurahViewHolder, position: Int) {
        holder.bindData(listData[position])
    }

    fun updateData(data: List<ProductListDetailTebusMurahDomainModel>) {
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }
}