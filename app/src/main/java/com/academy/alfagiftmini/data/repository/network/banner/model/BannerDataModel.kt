package com.academy.alfagiftmini.data.repository.network.banner.model


data class BannerDataModel(
    val id: Int,
    val bannerNameval : String,
    val bannerImageFileName: String,
    val startDate: String,
    val endDate: String,
    val deepLink: String
)
