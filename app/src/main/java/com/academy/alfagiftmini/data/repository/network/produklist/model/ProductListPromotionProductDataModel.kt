package com.academy.alfagiftmini.data.repository.network.produklist.model

import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel
import com.google.gson.annotations.SerializedName

data class ProductListPromotionProductDataModel(
    val id: Int,
    @SerializedName("product_id") val productId: Long,
    @SerializedName("promo_product_id") val promoProductId: List<Long>,
) {
    companion object {
        fun transforms(
            products: ArrayList<ProductListDetailDataModel>,
            sales: List<ProductListPromotionProductDataModel>,
            stocks: List<ProductListStockDetailDataModel>
        ): List<ProductListPromotionProductDomainModel> {
            val result = mutableListOf<ProductListPromotionProductDomainModel>()
            products.forEach { product ->
                val sale = sales.find { it.productId == product.productId }
                val stock = stocks.find { it.productId == product.productId }
                    result.add(transform(product, sale, stock))
            }
            return result
        }

        private fun transform(
            product: ProductListDetailDataModel,
            sale: ProductListPromotionProductDataModel?,
            stock: ProductListStockDetailDataModel?
        ): ProductListPromotionProductDomainModel {
            return ProductListPromotionProductDomainModel(id = product.id ?: 0,
                productId = product.productId ?: 0,
                stock = stock?.stock ?: 0,
                salesQuantity = product.salesQuantity ?: 0,
                officialStoreId = product.officialStoreId ?: 0,
                productSku = product.productSku ?: "",
                productName = product.productName ?: "",
                productDesc = product.productDesc ?: "",
                price = product.price ?: 0,
                productSpecialPrice = product.productSpecialPrice ?: 0,
                productImages = product.productImages?.map {
                    ProductListDetailDataModel.transformImages(
                        it
                    )
                } ?: listOf(),
                productPickupAvailability = product.productPickupAvailability ?: -1,
                productCategory = product.productCategory ?: "",
                kodeStore = product.kodeStore ?: "",
                kodePromo = product.kodePromo ?: listOf(),
                productGratis = sale?.promoProductId ?: listOf(),
                imgPreview103 = product.imgPreview103 ?: "")
        }
    }
}