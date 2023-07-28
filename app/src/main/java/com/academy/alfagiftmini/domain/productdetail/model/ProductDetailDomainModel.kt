package com.academy.alfagiftmini.domain.productdetail.model


data class  ProductDetailDomainModel(
    val productId: Long,
    val productSku: String,
    val productName: String,
    val productDesc: String,
    val status: Int,
    val price: Int,
    val productSpecialPrice: Int,
    val productImages: List<ImageDomainModel>,
    val productPickupAvailability: Int,
    val imagePreview103: String,
    val kodePromo: List<Int>
)

