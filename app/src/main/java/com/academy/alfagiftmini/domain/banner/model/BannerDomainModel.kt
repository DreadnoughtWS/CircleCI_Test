package com.academy.alfagiftmini.domain.banner.model

data class BannerDomainModel(
    val id: Int,
    val bannerNameval : String,
    val bannerImageFileName: String,
    val startDate: String,
    val endDate: String,
    val deepLink: String
)
