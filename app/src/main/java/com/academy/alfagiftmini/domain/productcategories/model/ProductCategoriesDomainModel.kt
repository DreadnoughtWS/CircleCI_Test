package com.academy.alfagiftmini.domain.productcategories.model

import com.academy.alfagiftmini.data.repository.network.produckcategories.model.SubCategoriesDataModel
import com.google.gson.annotations.SerializedName

data class ProductCategoriesDomainModel(
    val id: Int,
    val text: String,
    val image: String,
    val subcategories: List<String>
)
