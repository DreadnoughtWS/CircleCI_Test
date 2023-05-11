package com.academy.alfagiftmini.data.repository.network.productdetail.model

import com.academy.alfagiftmini.domain.productdetail.model.ProductPromosi103DomainModel
import com.google.gson.annotations.SerializedName

data class ProductPromosi103DataModel(
    val id: Int?,
    @SerializedName("product_id") val productId: Long?,
    @SerializedName("promo_product_id") val promoProductId: List<Long>?,
    @SerializedName("description") val description: String?
){
    companion object{
        fun transformToListEntity(item: List<ProductPromosi103DataModel?>?):List<ProductPromosi103DomainModel>{
                return item?.map {
                    transformsToEntity(it ?: ProductPromosi103DataModel(
                        id = 0,
                        productId = 0L,
                        promoProductId = listOf(),
                        description = ""
                    ))
                } ?: listOf()
        }

        fun transformsToEntity(it:ProductPromosi103DataModel):ProductPromosi103DomainModel{
            return ProductPromosi103DomainModel(
                id = it.id ?: 0,
                productId = it.productId ?: 0L,
                promoProductId = it.promoProductId ?: listOf(),
                description = it.description ?: ""
            )
        }
    }
}
