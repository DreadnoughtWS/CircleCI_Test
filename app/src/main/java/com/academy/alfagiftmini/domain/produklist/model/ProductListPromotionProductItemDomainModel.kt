package com.academy.alfagiftmini.domain.produklist.model

import com.google.gson.annotations.SerializedName

data class ProductListPromotionProductItemDomainModel(
    val id: Int,
    val productId: Long,
    val promoProductId: List<Int>,
)
