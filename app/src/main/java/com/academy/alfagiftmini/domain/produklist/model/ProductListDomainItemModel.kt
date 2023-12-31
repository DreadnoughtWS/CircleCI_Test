package com.academy.alfagiftmini.domain.produklist.model

import com.google.gson.annotations.SerializedName


data class ProductListDomainItemModel(
    val productId: Long,
    val productSku: String,
    val productName: String,
    val productDesc: String,
    val price: Int,
    val productSpecialPrice: Int?,
    val productImages: List<ProductListImagesDomainModel>,
    val productPickupAvailability: Int, // 1=toko, 0=gudang
    val productCategory: String,
    val id: Int,
    val kodeStore: String,
    val kodePromo: List<Int>,
    val stock: Int?,
    val salesQuantity: Int?,
    val officialStoreId: Int?,
    val imgPreview103: String?,
    )
