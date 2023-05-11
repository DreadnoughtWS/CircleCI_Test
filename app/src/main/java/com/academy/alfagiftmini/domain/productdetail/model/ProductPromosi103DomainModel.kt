package com.academy.alfagiftmini.domain.productdetail.model

data class ProductPromosi103DomainModel(
    val id: Int,
    val productId: Long,
    val promoProductId: List<Long>,
    val description: String
)
