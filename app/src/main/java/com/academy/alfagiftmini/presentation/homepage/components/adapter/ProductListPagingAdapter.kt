package com.academy.alfagiftmini.presentation.homepage.components.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ItemProductBinding
import com.academy.alfagiftmini.domain.produklist.model.ProductListDomainItemModel
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.PresentationUtils.hargaFormatter
import com.bumptech.glide.Glide

class ProductListPagingAdapter :
    PagingDataAdapter<ProductListDomainItemModel, ProductListPagingAdapter.ProductListViewHolder>(
        DiffCallback
    ) {


    companion object {
        object DiffCallback : DiffUtil.ItemCallback<ProductListDomainItemModel>() {
            override fun areItemsTheSame(
                oldItem: ProductListDomainItemModel, newItem: ProductListDomainItemModel
            ): Boolean {
                return (oldItem.id == newItem.id)
            }

            override fun areContentsTheSame(
                oldItem: ProductListDomainItemModel, newItem: ProductListDomainItemModel
            ): Boolean {
                return (oldItem == newItem)
            }

        }
    }


    class ProductListViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: ProductListDomainItemModel) {
            println(
                """
                name : ${data.productName}
                price : ${data.price}
                special price : ${data.productSpecialPrice}
                stock : ${data.stock}
            """.trimIndent()
            )


            with(binding) {
                tvNamaProduct.text = data.productName

                if (data.stock == null || data.stock <= 0) {
                    showStockKosong(data)
                    showTvStockFrom(data)
                    showImageProduct(data)
                    return
                }

                if (data.productSpecialPrice == null || data.productSpecialPrice == 0) {
                    showSpecialPriceNull(data)
                    showTvStockFrom(data)
                    showImageProduct(data)
                    return
                }

                if (data.productSpecialPrice < data.price) {
                    tvHargaDiskonProduct.text = "Rp. ${hargaFormatter(data.price)}"
                    tvHargaDiskonProduct.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

                    tvHargaProduct.text = "Rp. ${hargaFormatter(data.productSpecialPrice)}"

                    tvJumlahDiskon.text = buildString {
                        append(hitungDiskon(data))
                        append("%")
                    }
                } else {
                    tvHargaDiskonProduct.visibility = View.GONE
                    tvJumlahDiskon.visibility = View.GONE
                    tvHargaProduct.text = hargaFormatter(data.price)
                }



                showTvStockFrom(data)
                showImageProduct(data)


            }

        }

        private fun hitungDiskon(data: ProductListDomainItemModel): Int {
            val hargaAsli = data.price
            val hargaDiskon = data.productSpecialPrice ?: 0
            return ((hargaAsli - hargaDiskon) / hargaAsli.toDouble() * 100).toInt()
        }

        private fun showImageProduct(data: ProductListDomainItemModel) {
            Glide.with(itemView.context).load(data.productImages[0].url[0])
                .placeholder(R.drawable.uniliver_logo).into(binding.ivImage)
        }

        private fun showSpecialPriceNull(data: ProductListDomainItemModel) {
            with(binding) {
                tvHargaDiskonProduct.visibility = View.GONE
                tvJumlahDiskon.visibility = View.GONE
                tvHargaProduct.text = "Rp. ${hargaFormatter(data.price)}"
            }
        }

        private fun showStockKosong(data: ProductListDomainItemModel) {
            with(binding) {
                btnKeranjang.text = itemView.context.getString(R.string.stok_habis)
                btnKeranjang.isClickable = false
                btnKeranjang.setBackgroundDrawable(
                    AppCompatResources.getDrawable(
                        itemView.context, R.drawable.shape_btn_keranjang_sky_blue
                    )
                )
                btnKeranjang.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, 0, 0
                )

                tvHargaDiskonProduct.visibility = View.GONE
                tvJumlahDiskon.visibility = View.GONE


            }
        }

        private fun showTvStockFrom(data: ProductListDomainItemModel) {
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
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.bindData(getItem(position) ?: return)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductListViewHolder(binding)
    }
}