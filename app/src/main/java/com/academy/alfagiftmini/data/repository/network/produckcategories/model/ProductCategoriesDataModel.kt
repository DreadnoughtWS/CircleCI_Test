package com.academy.alfagiftmini.data.repository.network.produckcategories.model

import com.academy.alfagiftmini.domain.productcategories.model.ProductCategoriesDomainModel
import com.google.gson.annotations.SerializedName

data class ProductCategoriesDataModel(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("subcategory")
    val subcategories: List<SubCategoriesDataModel>
) {
    companion object {
        fun transform(it: List<ProductCategoriesDataModel>?) : List<ProductCategoriesDomainModel> {
            return it?.map {
                ProductCategoriesDomainModel(
                    it.id ?: 0,
                    it.text ?: "",
                    it.image ?: "",
                    SubCategoriesDataModel.transform(it.subcategories)
                )
            } ?: listOf()
        }
    }
}
