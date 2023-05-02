package com.academy.alfagiftmini.domain.produklist.model

data class ProductListDetailTebusMurahDomainModel (
    val productId: Long,
    val productName: String,
    val price: Int,
    val productSpecialPrice: Int?,
    val productImages: List<ProductListImagesDomainModel>,
    val productPickupAvailability: Int, // 1=toko, 0=gudang
    val id: Int,
        )