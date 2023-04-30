package com.academy.alfagiftmini.data.repository.network.produklist.model

import com.google.gson.annotations.SerializedName

data class ProductListPaketTebusMurahDataModel(
    val id:Int,
    @SerializedName("accordion_name") val accordionName:String,
    @SerializedName("promotion_product_id") val promotionProductId:List<Int>
)
