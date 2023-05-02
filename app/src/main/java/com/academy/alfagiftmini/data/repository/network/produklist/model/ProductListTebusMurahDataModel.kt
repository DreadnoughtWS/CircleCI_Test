package com.academy.alfagiftmini.data.repository.network.produklist.model

import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListDetailDataModel.Companion.transformImages
import com.academy.alfagiftmini.domain.produklist.model.ProductListDetailTebusMurahDomainModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListTebusMurahDomainModel
import com.google.gson.annotations.SerializedName

data class ProductListTebusMurahDataModel(
    val id: Int,
    @SerializedName("accordion_name") val accordionName: String,
    @SerializedName("promotion_product_id") val promotionProductId: List<Long>
) {
    companion object {
        fun transforms(
            tebusMurah: List<ProductListTebusMurahDataModel>,
            products: List<ProductListDetailDataModel>
        ): List<ProductListTebusMurahDomainModel> {
            return tebusMurah.map { transform(it, products) }
        }

        private fun transform(
            tebusMurah: ProductListTebusMurahDataModel,
            products: List<ProductListDetailDataModel>
        ): ProductListTebusMurahDomainModel {
            return ProductListTebusMurahDomainModel(
                id = tebusMurah.id,
                accordionName = tebusMurah.accordionName,
                tebusMurahProduct = transformsDetailTebusMurahDomainModel(
                    products.filter { tebusMurah.promotionProductId.contains(it.productId) }
                )
            )
        }

        private fun transformsDetailTebusMurahDomainModel(models:List<ProductListDetailDataModel>):List<ProductListDetailTebusMurahDomainModel>{
            return models.map { transformDetailTebusMurahDomainModel(it) }
        }

        private fun transformDetailTebusMurahDomainModel(model:ProductListDetailDataModel):ProductListDetailTebusMurahDomainModel{
            return ProductListDetailTebusMurahDomainModel(
                id = model.id ?: 0,
                productId = model.productId ?: 0,
                productName = model.productName ?: "",
                price = model.price ?: 0,
                productSpecialPrice = model.productSpecialPrice ?: 0,
                productImages = model.productImages?.map { transformImages(it) } ?: listOf(),
                productPickupAvailability = model.productPickupAvailability?:-1
            )
        }
    }
}
