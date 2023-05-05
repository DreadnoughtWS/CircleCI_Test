package com.academy.alfagiftmini.domain.productcategories.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductCategoriesDomainModel(
    val id: Int,
    val text: String,
    val image: String,
    val subcategories: List<String>
): Parcelable
