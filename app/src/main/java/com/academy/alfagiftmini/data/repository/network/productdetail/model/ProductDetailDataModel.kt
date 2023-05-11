package com.academy.alfagiftmini.data.repository.network.productdetail.model
import com.academy.alfagiftmini.domain.productdetail.model.ProductDetailDomainModel
import com.google.gson.annotations.SerializedName

data class ProductDetailDataModel(
    @SerializedName("product_id")
    val productId: Long?,
    @SerializedName("product_sku")
    val productSku: String?,
    @SerializedName("product_name")
    val productName: String?,
    @SerializedName("product_desc")
    val productDesc: String?,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("product_special_price")
    val productSpecialPrice: Int?,
    @SerializedName("product_images")
    val productImages: List<ImageDataModel?>?,
    @SerializedName("product_pickup_availability")
    val productPickupAvailability: Int?,
    @SerializedName("img_preview_103")
    val imagePreview103: String?,
    @SerializedName("kodePromo")
    val kodePromo: List<Int>?
){
    companion object{
        fun transformToListDomainModel(item: List<ProductDetailDataModel?>):List<ProductDetailDomainModel>{
            return item.map {
                transformToDomainModel(it ?: ProductDetailDataModel(
                    productId = 0L,
                    productSku = "",
                    productName = "",
                    productDesc = "",
                    status = 0,
                    price = 0,
                    productSpecialPrice = 0,
                    productImages = listOf(),
                    productPickupAvailability = 0,
                    imagePreview103 = "",
                    kodePromo = listOf()
                    )
                )

            }
        }

        private fun transformToDomainModel(it: ProductDetailDataModel):ProductDetailDomainModel{
            return ProductDetailDomainModel(
                it.productId ?: 0L,
                it.productSku ?: "",
                it.productName ?: "",
                it.productDesc ?: "",
                it.status ?: 0,
                it.price ?: 0,
                it.productSpecialPrice ?: 0,
                ImageDataModel.transformToDomainList(it.productImages ?: listOf()),
                it.productPickupAvailability ?: 0,
                it.imagePreview103 ?: "",
                it.kodePromo ?: listOf()
            )
        }
    }
}
