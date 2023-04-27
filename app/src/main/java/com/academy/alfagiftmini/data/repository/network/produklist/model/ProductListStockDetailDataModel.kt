package com.academy.alfagiftmini.data.repository.network.produklist.model

import com.google.gson.annotations.SerializedName

data class ProductListStockDetailDataModel(
    @SerializedName("product_id") val productId:Long?,
    val stock:Int?
)
