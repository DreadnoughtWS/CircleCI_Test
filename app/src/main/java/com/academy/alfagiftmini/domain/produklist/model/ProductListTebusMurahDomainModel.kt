package com.academy.alfagiftmini.domain.produklist.model

data class ProductListTebusMurahDomainModel(
    val id: Int,
    val accordionName: String,
    val tebusMurahProduct:List<ProductListDetailTebusMurahDomainModel>
)
