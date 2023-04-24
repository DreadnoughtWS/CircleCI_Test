package com.academy.alfagiftmini.domain.produklist.model


data class ProductListStockResponseDomainModel(
    val kodeArea:String,
    val productDetails:List<ProductListStockDetailDomainModel>
)
