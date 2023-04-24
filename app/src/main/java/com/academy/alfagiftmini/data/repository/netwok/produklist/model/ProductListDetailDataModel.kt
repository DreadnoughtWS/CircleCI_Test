package com.academy.alfagiftmini.data.repository.netwok.produklist.model

import com.academy.alfagiftmini.domain.produklist.model.ProductListDomainItemModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListImagesDomainModel
import com.google.gson.annotations.SerializedName

data class ProductListDetailDataModel(
    @SerializedName("product_id") val productId: Long?,
    @SerializedName("product_sku") val productSku: String?,
    @SerializedName("product_name") val productName: String?,
    @SerializedName("product_desc") val productDesc: String?,
    @SerializedName("price") val price: Int?,
    @SerializedName("product_special_price") val productSpecialPrice: Int?,
    @SerializedName("product_images") val productImages: List<ProductListImagesDataModel>?,
    @SerializedName("product_pickup_availability") val productPickupAvailability: Int?, // 1=toko, 0=gudang
    @SerializedName("product_category") val productCategory: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("kodeStore") val kodeStore: String?,
    @SerializedName("kodePromo") val kodePromo: List<Int>?
) {
    companion object {
        fun transforms(
            products: List<ProductListDetailDataModel>,
            stocks: List<ProductListStockDetailDataModel>
        ): List<ProductListDomainItemModel> {
            val result = mutableListOf<ProductListDomainItemModel>()
            products.forEach { product ->
                val stock = stocks.find { it.productId == product.productId }
                if (stock != null) {
                    result.add(transform(product, stock))
                }
            }
            return result
        }

        private fun transform(
            product: ProductListDetailDataModel, stock: ProductListStockDetailDataModel
        ): ProductListDomainItemModel {
            return ProductListDomainItemModel(productId = product.productId ?: 0,
                id = product.id ?: 0,
                productSku = product.productSku ?: "",
                productName = product.productName ?: "",
                productDesc = product.productDesc ?: "",
                price = product.price ?: 0,
                productSpecialPrice = product.productSpecialPrice ?: 0,
                productImages = product.productImages?.map { transformImages(it) } ?: listOf(),
                productPickupAvailability = product.productPickupAvailability ?: -1,
                productCategory = product.productCategory ?: "",
                kodeStore = product.kodeStore ?: "",
                kodePromo = product.kodePromo ?: listOf(),
                stock = stock.stock ?: 0)
        }

        private fun transformImages(image: ProductListImagesDataModel): ProductListImagesDomainModel {
            return ProductListImagesDomainModel(
                type = image.type ?: "", url = image.url ?: listOf()
            )
        }
    }
}
