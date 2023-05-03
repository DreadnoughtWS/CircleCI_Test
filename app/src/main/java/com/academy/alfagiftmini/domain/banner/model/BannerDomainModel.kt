package com.academy.alfagiftmini.domain.banner.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BannerDomainModel(
    val id: Int,
    val bannerName : String,
    val bannerImageFileName: String,
    val startDate: String,
    val endDate: String,
    val deepLink: String
) : Parcelable
