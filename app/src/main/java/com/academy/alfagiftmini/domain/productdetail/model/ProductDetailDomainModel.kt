package com.academy.alfagiftmini.domain.productdetail.model


data class ProductDetailDomainModel(
    val productId: Int,
    val productSku: String,
    val productName: String,
    val productDesc: String,
    val status: Int,
    val price: Int,
    val productSpecialPrice: Int,
    val productImages: List<ImageDomainModel>,
    val productPickupAvailability: Int
)

