package com.academy.alfagiftmini.domain.produklist.model

import com.google.gson.annotations.SerializedName

data class ProductListPaketTebusMurahDomainModel(
    val id: Int,
    val accordionName: String,
    val promotionProductId: List<Int>
)
