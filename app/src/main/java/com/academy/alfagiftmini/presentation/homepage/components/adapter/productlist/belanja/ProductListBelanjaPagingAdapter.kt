package com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.belanja

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ItemProductGratisProductBinding
import com.academy.alfagiftmini.databinding.ItemProdukBelanjaBinding
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.components.activity.banner.BannerPromoItemListActivity
import com.academy.alfagiftmini.presentation.homepage.components.activity.productdetail.ProductDetailActivity
import com.academy.alfagiftmini.presentation.homepage.components.adapter.productlist.ProductListGratisProductPagingAdapter
import com.bumptech.glide.Glide

class ProductListBelanjaPagingAdapter : PagingDataAdapter<ProductListPromotionProductDomainModel, ProductListBelanjaPagingAdapter.ProductListViewHolder>(
    DiffCallback
) {
    lateinit var context: Context
    companion object {
        object DiffCallback : DiffUtil.ItemCallback<ProductListPromotionProductDomainModel>() {
            override fun areItemsTheSame(
                oldItem: ProductListPromotionProductDomainModel,
                newItem: ProductListPromotionProductDomainModel
            ): Boolean {
                return (oldItem.id == newItem.id)
            }

            override fun areContentsTheSame(
                oldItem: ProductListPromotionProductDomainModel,
                newItem: ProductListPromotionProductDomainModel
            ): Boolean {
                return (oldItem == newItem)
            }

        }
    }

    class ProductListViewHolder(private val binding: ItemProdukBelanjaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: ProductListPromotionProductDomainModel, context:Context) {
            with(binding) {

                tvProductName.text = data.productName

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
                    tvHargaDiskonProduct.text =
                        "Rp. ${PresentationUtils.hargaFormatter(data.price)}"
                    tvHargaDiskonProduct.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

                    tvHargaProduct.text =
                        "Rp. ${PresentationUtils.hargaFormatter(data.productSpecialPrice)}"

                    tvJumlahDiskon.text = buildString {
                        append(hitungDiskon(data))
                        append("%")
                    }
                } else {
                    tvHargaDiskonProduct.visibility = View.GONE
                    tvJumlahDiskon.visibility = View.GONE
                    tvHargaProduct.text = "Rp. ${PresentationUtils.hargaFormatter(data.price)}"
                }



                showTvStockFrom(data)
                showImageProduct(data)

                root.setOnClickListener {
                    val intent = Intent(context, ProductDetailActivity::class.java)
                    intent.putExtra(PresentationUtils.PRODUCT_ID, data.productId)
                    context.startActivity(intent)
                }

            }

        }
        private fun hitungDiskon(data: ProductListPromotionProductDomainModel): Int {
            val hargaAsli = data.price
            val hargaDiskon = data.productSpecialPrice ?: 0
            return ((hargaAsli - hargaDiskon) / hargaAsli.toDouble() * 100).toInt()
        }

        private fun showImageProduct(data: ProductListPromotionProductDomainModel) {
            Glide.with(itemView.context).load(data.productImages[0].url[0])
                .placeholder(R.drawable.uniliver_logo).into(binding.ivProductLogo)
            if (data.imgPreview103.isNullOrBlank()) {
                binding.llProductGratis.visibility = View.GONE
                return
            }
            Glide.with(itemView.context).load(data.imgPreview103)
                .placeholder(R.drawable.uniliver_logo).into(binding.civProdukGratis)
            if (data.productGratis.size > 1) {
                binding.civProdukGratisLebih.visibility = View.VISIBLE
            } else {
                binding.civProdukGratisLebih.visibility = View.GONE
            }

        }

        private fun showSpecialPriceNull(data: ProductListPromotionProductDomainModel) {
            with(binding) {
                tvHargaDiskonProduct.visibility = View.GONE
                tvJumlahDiskon.visibility = View.GONE
                tvHargaProduct.text = "Rp. ${PresentationUtils.hargaFormatter(data.price)}"
            }
        }

        private fun showStockKosong(data: ProductListPromotionProductDomainModel) {
            with(binding) {
                clPlusMinusProduct.visibility = View.GONE
                btnKeranjang.visibility = View.VISIBLE
                btnKeranjang.isClickable = false
                tvHargaDiskonProduct.visibility = View.GONE
                tvJumlahDiskon.visibility = View.GONE


            }
        }

        private fun showTvStockFrom(data: ProductListPromotionProductDomainModel) {
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

    override fun onBindViewHolder(
        holder: ProductListViewHolder,
        position: Int
    ) {
        holder.bindData(getItem(position) ?:return,context)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductListBelanjaPagingAdapter.ProductListViewHolder {
        context = parent.context
        val binding = ItemProdukBelanjaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductListViewHolder(binding)
    }
}